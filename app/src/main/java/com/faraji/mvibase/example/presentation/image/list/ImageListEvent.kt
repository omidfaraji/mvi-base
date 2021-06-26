package com.faraji.mvibase.example.presentation.image.list

import com.faraji.mvibase.presentation.MviViewEvent

sealed class ImageListEvent : MviViewEvent {
    class Error(val message: String) : ImageListEvent()

}