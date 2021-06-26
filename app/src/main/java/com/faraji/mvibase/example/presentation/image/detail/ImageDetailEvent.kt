package com.faraji.mvibase.example.presentation.image.detail

import com.faraji.mvibase.presentation.MviViewEvent

sealed class ImageDetailEvent : MviViewEvent {
    class Error(val message: String) : ImageDetailEvent()
}