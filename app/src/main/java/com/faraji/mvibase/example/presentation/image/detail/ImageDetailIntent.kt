package com.faraji.mvibase.example.presentation.image.detail

import com.faraji.mvibase.presentation.MviIntent

sealed class ImageDetailIntent : MviIntent {
    class Init(val imageId: String) : ImageDetailIntent()
}