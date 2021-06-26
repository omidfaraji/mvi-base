package com.faraji.mvibase.example.presentation.image.list

import com.faraji.mvibase.presentation.MviIntent

sealed class ImageListIntent : MviIntent {
    object LoadMore : ImageListIntent()
    object Refresh : ImageListIntent()
}