package com.faraji.mvibase.example.presentation.image.list

import com.faraji.mvibase.example.presentation.model.ImageListItem
import com.faraji.mvibase.presentation.MviViewState

data class ImageListViewState(
    val loading: Boolean = false,
    val imageItems: List<ImageListItem> = listOf(),
) : MviViewState