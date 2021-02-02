package com.appcraft.domain.gateway

import com.appcraft.domain.model.TvDetailItem
import com.appcraft.domain.model.TvShowItemMP
import com.appcraft.domain.model.TvShowMostPopular

interface FilmsGateway {

    suspend fun getFilmByPage(page: Int): TvShowMostPopular

    suspend fun getDetailById(id: Long): TvDetailItem

    suspend fun addTvShowToDB(tvShowItemMP: TvShowItemMP)

    suspend fun getTvShowByIdFromDB(id: Long) :TvShowItemMP?

    suspend fun getAllTvShowFromDB() : List<TvShowItemMP>

    suspend fun deleteItemFromDB(tvShowItemMP: TvShowItemMP)

}