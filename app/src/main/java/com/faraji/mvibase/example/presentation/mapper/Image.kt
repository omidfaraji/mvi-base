package com.faraji.mvibase.example.presentation.mapper

import com.faraji.mvibase.example.domain.model.Image
import com.faraji.mvibase.example.presentation.model.ImageListItem

fun Image.toUi(): ImageListItem =
    ImageListItem(id, url)