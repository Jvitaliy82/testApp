package com.appcraft.data.network

import com.appcraft.data.network.model.SampleModel
import com.appcraft.data.network.model.TvDetailModel
import com.appcraft.data.network.model.TvShowMostPopularModel
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
    ): TvShowMostPopularModel

    @GET("show-details")
    suspend fun getDetails(
        @Query("q") tvShowId: Long,
    ): TvDetailModel
}