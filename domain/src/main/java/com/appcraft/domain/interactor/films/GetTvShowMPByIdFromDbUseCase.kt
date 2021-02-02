package com.appcraft.domain.interactor.films

import com.appcraft.domain.gateway.FilmsGateway
import com.appcraft.domain.global.interactor.UseCaseWithParams
import com.appcraft.domain.model.TvShowItemMP
import kotlinx.coroutines.Dispatchers

class GetTvShowMPByIdFromDbUseCase(
    private val filmsGateway: FilmsGateway
) : UseCaseWithParams<Long, TvShowItemMP?>(Dispatchers.IO) {
    override suspend fun execute(id: Long) =
        filmsGateway.getTvShowByIdFromDB(id)
}