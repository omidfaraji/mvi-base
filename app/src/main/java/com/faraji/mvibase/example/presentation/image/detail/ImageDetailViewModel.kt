package com.faraji.mvibase.example.presentation.image.detail

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import com.faraji.mvibase.example.R
import com.faraji.mvibase.example.domain.usecase.GetFullImageUseCase
import com.faraji.mvibase.presentation.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class ImageDetailViewModel @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val getFullImageUseCase: GetFullImageUseCase,
    private val savedStateHandle: SavedStateHandle?
) : MviViewModel<ImageDetailIntent, ImageDetailAction, ImageDetailViewState, ImageDetailEvent>() {


    override fun getInitialState(): ImageDetailViewState = ImageDetailViewState()

    private lateinit var imageId: String

    private suspend fun loadImage() {
        sendState(stateValue.copy(loading = true))
        try {
            val image = getFullImageUseCase.execute(imageId)
            sendState(stateValue.copy(image = image.url, loading = false))
        } catch (e: Exception) {
            sendState(
                stateValue.copy(
                    error = e.message ?: context.getString(R.string.unknownError),
                    loading = false
                )
            )
        }
    }


    override fun intentToAction(intent: ImageDetailIntent): ImageDetailAction {
        return when (intent) {
            is ImageDetailIntent.Init -> ImageDetailAction.Init(intent.imageId)
        }
    }


    override suspend fun handleActions(action: ImageDetailAction) {
        when (action) {
            is ImageDetailAction.Init -> {
                imageId = action.imageId
                loadImage()
            }
        }
    }


}