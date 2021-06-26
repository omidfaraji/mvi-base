package com.faraji.mvibase.example.domain.repository

import com.faraji.mvibase.example.domain.model.Image
import com.faraji.mvibase.example.domain.model.request.Size

interface CatRepository {

    suspend fun getThumbs(
        page: Int,
        limit: Int,
        size: Size,
    ): List<Image>

    suspend fun getFullImage(
        id: String,
        size: Size,
    ): Image

}