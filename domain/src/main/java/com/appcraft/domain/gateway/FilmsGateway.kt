package com.appcraft.domain.gateway

import com.appcraft.domain.model.TvDetailModel
import com.appcraft.domain.model.TvShowModel

interface FilmsGateway {
    suspend fun getFilmByPage(page: Int): TvShowModel
    suspend fun getDetailById(id: Int): TvDetailModel
    suspend fun addTvShowToDB(id: TvShowModel.TvShowX)
}