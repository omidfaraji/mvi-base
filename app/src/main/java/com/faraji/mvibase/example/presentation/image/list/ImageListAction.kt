package com.faraji.mvibase.example.presentation.image.list

import com.faraji.mvibase.presentation.MviAction

sealed class ImageListAction : MviAction {
    object LoadMore : ImageListAction()
    object Refresh : ImageListAction()

}