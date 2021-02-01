package com.appcraft.domain.gateway

import com.appcraft.domain.model.TvDetail
import com.appcraft.domain.model.TvShowItemMP
import com.appcraft.domain.model.TvShowMostPopular

interface FilmsGateway {

    suspend fun getFilmByPage(page: Int): TvShowMostPopular

    suspend fun getDetailById(id: Int): TvDetail

    suspend fun addTvShowToDB(id: TvShowItemMP)
}