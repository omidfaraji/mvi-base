package com.faraji.mvibase.example.data.mapper

import com.faraji.mvibase.example.data.model.ImageData
import com.faraji.mvibase.example.domain.model.Image

fun ImageData.toDomain(): Image {
    return Image(id, url)
}
