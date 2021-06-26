package com.faraji.mvibase.example.domain.usecase

import com.faraji.mvibase.example.domain.model.Image
import com.faraji.mvibase.example.domain.model.request.Size
import com.faraji.mvibase.example.domain.repository.CatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetThumbsUseCaseImpl @Inject constructor(
    private val catRepository: CatRepository
) : GetThumbsUseCase {


    override suspend fun execute(page: Int, limit: Int, size: Size): List<Image> {
        return withContext(Dispatchers.IO) {
            catRepository.getThumbs(page, limit, size)
        }
    }


}
