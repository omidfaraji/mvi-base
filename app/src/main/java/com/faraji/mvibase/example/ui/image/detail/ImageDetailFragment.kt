package com.faraji.mvibase.example.ui.image.detail

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.faraji.mvibase.example.databinding.FragmentImageDetailBinding
import com.faraji.mvibase.example.presentation.image.detail.ImageDetailEvent
import com.faraji.mvibase.example.presentation.image.detail.ImageDetailIntent
import com.faraji.mvibase.example.presentation.image.detail.ImageDetailViewModel
import com.faraji.mvibase.example.presentation.image.detail.ImageDetailViewState
import com.faraji.mvibase.example.utils.extension.load
import com.faraji.mvibase.example.utils.extension.showToast
import com.faraji.mvibase.view.MviFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.merge


class ImageDetailFragment :
    MviFragment<FragmentImageDetailBinding, ImageDetailIntent, ImageDetailViewState, ImageDetailEvent, ImageDetailViewModel>() {

    override val binding by lazy { FragmentImageDetailBinding.inflate(layoutInflater) }

    override val viewModel: ImageDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val imageId = ImageDetailFragmentArgs.fromBundle(requireArguments()).imageId
        sendIntent(ImageDetailIntent.Init(imageId))
    }


    override fun intents(): Flow<ImageDetailIntent> {
        return merge()
    }

    override suspend fun handleState(state: ImageDetailViewState) {
        with(state) {
            binding.photoView.load(image, lifecycleScope, onFail = {
                showToast("Can not get Image from url: $image")
            })
            error?.let { showToast(it) }
        }
    }

    override suspend fun handleEvent(event: ImageDetailEvent) {
        when (event) {
            is ImageDetailEvent.Error -> showToast(event.message)
        }
    }


}