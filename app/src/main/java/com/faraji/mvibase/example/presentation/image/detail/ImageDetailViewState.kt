package com.faraji.mvibase.example.presentation.image.detail

import com.faraji.mvibase.presentation.MviViewState

data class ImageDetailViewState(
    val loading: Boolean = false,
    val image: String = "",
    val error: String? = null
) : MviViewState