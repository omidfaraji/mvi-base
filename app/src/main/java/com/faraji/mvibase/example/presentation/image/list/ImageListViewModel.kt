package com.faraji.mvibase.example.presentation.image.list

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.faraji.mvibase.example.R
import com.faraji.mvibase.example.domain.usecase.GetThumbsUseCase
import com.faraji.mvibase.example.presentation.mapper.toUi
import com.faraji.mvibase.example.presentation.model.ImageListItem
import com.faraji.mvibase.presentation.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ImageListViewModel @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val getThumbsUseCase: GetThumbsUseCase,
) : MviViewModel<ImageListIntent, ImageListAction, ImageListViewState, ImageListEvent>() {
    companion object {
        const val LIMIT = 20
    }


    private val imageItems = mutableListOf<ImageListItem>()
    private var page: Int = 0
    private var isEnd = false

    override fun getInitialState(): ImageListViewState = ImageListViewState()


    init {
        viewModelScope.launch {
            loadInitData()
        }
    }


    private suspend fun loadInitData() {
        try {
            loadingImages()
        } catch (e: Exception) {
            onError(e)
        }
    }

    private suspend fun loadNextPage() {
        try {
            page++
            loadingImages()
        } catch (e: Exception) {
            page--
            onError(e)
        }
    }


    private suspend fun refresh() {
        val oldPage = page
        val oldIsEnd = isEnd
        val oldItems = imageItems
        try {
            page = 0
            imageItems.clear()
            loadingImages()
            isEnd = false
        } catch (e: Exception) {
            page = oldPage
            isEnd = oldIsEnd
            imageItems.apply { addAll(oldItems) }
            onError(e)
        }
    }

    private suspend fun loadingImages() {
        sendState(stateValue.copy(loading = true))
        val images = getThumbsUseCase.execute(page, LIMIT)
        if (images.isEmpty()) {
            isEnd = true
            sendState(stateValue.copy(loading = false))
            return
        }
        imageItems.addAll(images.map { it.toUi() })
        sendState(stateValue.copy(imageItems = imageItems.toList(), loading = false))
    }

    private fun onError(e: Exception) {
        Timber.e(e)
        sendState(stateValue.copy(loading = false))
        sendEvent(
            ImageListEvent.Error(
                message = e.message ?: context.getString(R.string.unknownError),
            )
        )
    }


    override fun intentToAction(intent: ImageListIntent): ImageListAction {
        return when (intent) {
            is ImageListIntent.LoadMore -> ImageListAction.LoadMore
            is ImageListIntent.Refresh -> ImageListAction.Refresh
        }
    }

    override suspend fun handleActions(action: ImageListAction) {
        when (action) {
            is ImageListAction.LoadMore -> loadNextPage()
            is ImageListAction.Refresh -> refresh()
        }
    }


}