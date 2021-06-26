package com.faraji.mvibase.example.ui.image.list

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.faraji.mvibase.example.databinding.FragmentImageListBinding
import com.faraji.mvibase.example.presentation.image.list.ImageListEvent
import com.faraji.mvibase.example.presentation.image.list.ImageListIntent
import com.faraji.mvibase.example.presentation.image.list.ImageListViewModel
import com.faraji.mvibase.example.presentation.image.list.ImageListViewState
import com.faraji.mvibase.example.utils.SpaceItemDecoration
import com.faraji.mvibase.example.utils.extension.dp
import com.faraji.mvibase.example.utils.extension.loadMores
import com.faraji.mvibase.example.utils.extension.showToast
import com.faraji.mvibase.view.MviFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import ru.ldralighieri.corbind.swiperefreshlayout.refreshes


@AndroidEntryPoint
class ImageListFragment :
    MviFragment<FragmentImageListBinding, ImageListIntent, ImageListViewState, ImageListEvent, ImageListViewModel>() {

    override val binding by lazy { FragmentImageListBinding.inflate(layoutInflater) }

    override val viewModel: ImageListViewModel by viewModels()

    private val adapter by lazy {
        ImageListAdapter(onItemClicked = {
            findNavController().navigate(
                ImageListFragmentDirections.actionImageListToImageDetail(
                    it.id
                )
            )
        })
    }

    override fun initView(savedInstanceState: Bundle?) {
        initImageList()
    }

    private fun initImageList() {
        binding.recycler.adapter = adapter
        if (binding.recycler.itemDecorationCount == 0)
            binding.recycler.addItemDecoration(
                SpaceItemDecoration(5.dp.toInt(), SpaceItemDecoration.GRID_LAYOUT_MANAGER)
            )
    }


    override fun intents(): Flow<ImageListIntent> {
        return merge(
            binding.srRefresh.refreshes().map { ImageListIntent.Refresh },
            binding.recycler.loadMores().map { ImageListIntent.LoadMore }
        )
    }

    override suspend fun handleState(state: ImageListViewState) {
        with(state) {
            binding.srRefresh.isRefreshing = loading
            adapter.submitList(imageItems)
        }
    }

    override suspend fun handleEvent(event: ImageListEvent) {
        when (event) {
            is ImageListEvent.Error -> showToast(event.message)
        }
    }
}