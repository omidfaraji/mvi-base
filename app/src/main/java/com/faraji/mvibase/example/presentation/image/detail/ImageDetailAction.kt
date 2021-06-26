package com.faraji.mvibase.example.presentation.image.detail

import com.faraji.mvibase.presentation.MviAction

sealed class ImageDetailAction : MviAction {
    class Init(val imageId: String) : ImageDetailAction()
}