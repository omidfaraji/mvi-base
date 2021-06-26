package com.faraji.mvibase.example.domain.usecase

import com.faraji.mvibase.example.domain.model.Image
import com.faraji.mvibase.example.domain.model.request.Size

interface GetThumbsUseCase {
    suspend fun execute(page: Int, limit: Int, size: Size = Size.THUMB): List<Image>
}