package com.faraji.mvibase.example.data.source.remote

import com.faraji.mvibase.example.data.model.ImageData
import com.faraji.mvibase.example.data.model.SizeData
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface CatRestService {
    companion object {
        const val API_KEY = "5e79d5ae-0752-438b-bb23-0173e244a024"
    }

    @GET("/v1/images/search")
    suspend fun getThumbs(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("size") sizeData: SizeData = SizeData.THUMB,
//        @Query("order") order: String = "DESC",
        @Header("x-api-key") apiKey: String = API_KEY,
    ): List<ImageData>

    @GET("/v1/images/{id}")
    suspend fun getFullImage(
        @Path("id") id: String,
        @Query("size") sizeData: SizeData = SizeData.FULL,
        @Header("x-api-key") apiKey: String = API_KEY,
    ): ImageData
}