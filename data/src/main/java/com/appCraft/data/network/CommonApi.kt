package com.appCraft.data.network

import com.appCraft.data.network.model.SampleModel
import com.appCraft.domain.model.TvShowModel
import retrofit2.http.GET
import retrofit2.http.Query

interface CommonApi {
    @GET("/sample/api")
    suspend fun getSampleData(
        @Query("parameter") parameter: Double
    ): SampleModel

    @GET("most-popular")
    suspend fun getMostPopularTV(
        @Query("page") page: Int,
    ): TvShowModel
}