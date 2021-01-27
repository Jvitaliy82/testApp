package com.appCraft.data.gateway

import com.appCraft.data.network.CommonApi
import com.appCraft.domain.gateway.FilmsGateway
import com.appCraft.domain.model.TvShowModel

class FilmGatewayImpl(
    private val commonApi: CommonApi,
) : FilmsGateway {

    override suspend fun getFilmByPage(page: Int) : TvShowModel =
        commonApi.getMostPopularTV(page)

}
