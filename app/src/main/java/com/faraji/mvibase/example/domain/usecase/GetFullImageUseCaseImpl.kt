package com.faraji.mvibase.example.domain.usecase

import com.faraji.mvibase.example.domain.model.Image
import com.faraji.mvibase.example.domain.model.request.Size
import com.faraji.mvibase.example.domain.repository.CatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetFullImageUseCaseImpl @Inject constructor(
    private val catRepository: CatRepository
) : GetFullImageUseCase {


    override suspend fun execute(id: String, size: Size): Image {
        return withContext(Dispatchers.IO) {
            catRepository.getFullImage(id, size)
        }
    }


}
