package com.appCraft.data.gateway

import com.appCraft.data.network.CommonApi
import com.appCraft.domain.gateway.FilmsGateway
import com.appCraft.domain.model.TvDetailModel
import com.appCraft.domain.model.TvShowModel

class FilmGatewayImpl(
    private val commonApi: CommonApi,
) : FilmsGateway {

    override suspend fun getFilmByPage(page: Int) : TvShowModel =
        commonApi.getMostPopularTV(page)

    override suspend fun getDetailById(id: String): TvDetailModel =
        commonApi.getDetails(id)

}
