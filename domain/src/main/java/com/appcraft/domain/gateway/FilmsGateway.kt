package com.appcraft.domain.gateway

import com.appcraft.domain.model.TvDetail
import com.appcraft.domain.model.TvShowItemMP
import com.appcraft.domain.model.TvShowMostPopular

interface FilmsGateway {

    suspend fun getFilmByPage(page: Int): TvShowMostPopular

    suspend fun getDetailById(id: Long): TvDetail

    suspend fun addTvShowToDB(tvShowItemMP: TvShowItemMP)

    suspend fun getTvShowByNameFromDB(name: String) : List<TvShowItemMP>

    suspend fun getAllTvShowFromDB() : List<TvShowItemMP>

    suspend fun deleteItemFromDB(tvShowItemMP: TvShowItemMP)

}