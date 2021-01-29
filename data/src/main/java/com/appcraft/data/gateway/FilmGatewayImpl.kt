package com.appcraft.data.gateway

import com.appcraft.data.network.CommonApi
import com.appcraft.domain.gateway.FilmsGateway
import com.appcraft.domain.model.TvDetailModel
import com.appcraft.domain.model.TvShowModel

class FilmGatewayImpl(
    private val commonApi: CommonApi,
) : FilmsGateway {

    override suspend fun getFilmByPage(page: Int) : TvShowModel =
        commonApi.getMostPopularTV(page)

    override suspend fun getDetailById(id: Int): TvDetailModel =
        commonApi.getDetails(id)

    override suspend fun addTvShowToDB(id: TvShowModel.TvShowX) {
        //todo add repository
    }
}
