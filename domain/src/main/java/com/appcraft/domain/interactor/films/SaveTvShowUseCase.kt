package com.appcraft.domain.interactor.films

import com.appcraft.domain.gateway.FilmsGateway
import com.appcraft.domain.global.interactor.UseCaseWithParams
import com.appcraft.domain.model.TvShowModel
import kotlinx.coroutines.Dispatchers

class SaveTvShowUseCase(
    private val filmsGateway: FilmsGateway
) : UseCaseWithParams<TvShowModel.TvShowX, Unit>(Dispatchers.IO) {
    override suspend fun execute(parameters: TvShowModel.TvShowX): Unit =
        filmsGateway.addTvShowToDB(parameters)
}