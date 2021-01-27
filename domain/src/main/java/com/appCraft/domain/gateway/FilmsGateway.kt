package com.appCraft.domain.gateway

import com.appCraft.domain.model.TvDetailModel
import com.appCraft.domain.model.TvShowModel

interface FilmsGateway {
    suspend fun getFilmByPage(page: Int) : TvShowModel
    suspend fun getDetailById(id: String) : TvDetailModel
}