package com.appcraft.data.gateway

import com.appcraft.data.network.CommonApi
import com.appcraft.data.network.model.toTvDetail
import com.appcraft.data.network.model.toTvShowMostPopular
import com.appcraft.domain.gateway.FilmsGateway
import com.appcraft.domain.model.TvDetail
import com.appcraft.domain.model.TvShowItemMP
import com.appcraft.domain.model.TvShowMostPopular

class FilmGatewayImpl(
    private val commonApi: CommonApi,
) : FilmsGateway {

    override suspend fun getFilmByPage(page: Int) : TvShowMostPopular =
        commonApi.getMostPopularTV(page).toTvShowMostPopular()

    override suspend fun getDetailById(id: Int): TvDetail =
        commonApi.getDetails(id).toTvDetail()

    override suspend fun addTvShowToDB(id: TvShowItemMP) {
        //todo add repository
    }
}
