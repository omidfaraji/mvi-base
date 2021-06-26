package com.faraji.mvibase.example.domain.usecase

import com.faraji.mvibase.example.domain.model.Image
import com.faraji.mvibase.example.domain.model.request.Size

interface GetFullImageUseCase {
    suspend fun execute(id: String, size: Size = Size.FULL): Image
}