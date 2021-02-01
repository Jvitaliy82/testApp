package com.appcraft.domain.interactor.films

import com.appcraft.domain.gateway.FilmsGateway
import com.appcraft.domain.global.interactor.UseCaseWithParams
import com.appcraft.domain.model.TvShowItemMP
import kotlinx.coroutines.Dispatchers

class AddTvShowMPUseCase(
    private val filmsGateway: FilmsGateway
) : UseCaseWithParams<TvShowItemMP, Unit>(Dispatchers.IO) {
    override suspend fun execute(tvShowItemMP: TvShowItemMP) =
        filmsGateway.addTvShowToDB(tvShowItemMP)
}