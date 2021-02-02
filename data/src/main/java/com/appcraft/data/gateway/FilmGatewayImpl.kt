package com.appcraft.data.gateway

import com.appcraft.data.network.CommonApi
import com.appcraft.data.network.model.toTvDetailItem
import com.appcraft.data.network.model.toTvShowMostPopular
import com.appcraft.data.storage.repository.TvShowRepository
import com.appcraft.data.storage.toTvShowItemMP
import com.appcraft.data.storage.toTvShowMPEntity
import com.appcraft.domain.gateway.FilmsGateway
import com.appcraft.domain.model.TvDetailItem
import com.appcraft.domain.model.TvShowItemMP
import com.appcraft.domain.model.TvShowMostPopular

class FilmGatewayImpl(
    private val commonApi: CommonApi,
    private val tvShowRepository: TvShowRepository
) : FilmsGateway {

    override suspend fun getFilmByPage(page: Int) : TvShowMostPopular =
        commonApi.getMostPopularTV(page).toTvShowMostPopular()

    override suspend fun getDetailById(id: Long): TvDetailItem =
        commonApi.getDetails(id).toTvDetailItem()

    override suspend fun addTvShowToDB(tvShowItemMP: TvShowItemMP) {
        if (tvShowRepository.getTvShowByName(tvShowItemMP.name).isEmpty()) {
            tvShowRepository.addTvShow(tvShowItemMP.toTvShowMPEntity())
        }
    }

    override suspend fun getTvShowByNameFromDB(name: String): List<TvShowItemMP> =
        tvShowRepository.getTvShowByName(name).map { it.toTvShowItemMP() }


    override suspend fun getAllTvShowFromDB(): List<TvShowItemMP> =
        tvShowRepository.getAllTvShow().map { it.toTvShowItemMP() }

    override suspend fun deleteItemFromDB(tvShowItemMP: TvShowItemMP) {
        tvShowRepository.deleteItem(tvShowItemMP.toTvShowMPEntity())
    }
}
