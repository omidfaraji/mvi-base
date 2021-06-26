package com.faraji.mvibase.example.data.repository

import com.faraji.mvibase.example.data.mapper.toData
import com.faraji.mvibase.example.data.mapper.toDomain
import com.faraji.mvibase.example.data.source.remote.CatRestService
import com.faraji.mvibase.example.domain.model.Image
import com.faraji.mvibase.example.domain.model.request.Size
import com.faraji.mvibase.example.domain.repository.CatRepository
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(
    private val catRestService: CatRestService,
) : CatRepository {

    override suspend fun getThumbs(page: Int, limit: Int, size: Size): List<Image> =
        catRestService.getThumbs(page, limit, size.toData()).map { it.toDomain() }

    override suspend fun getFullImage(id: String, size: Size): Image =
        catRestService.getFullImage(id, size.toData()).toDomain()

}

