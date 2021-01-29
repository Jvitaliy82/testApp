package com.appCraft.domain.interactor.films

import com.appCraft.domain.gateway.FilmsGateway
import com.appCraft.domain.global.interactor.UseCaseWithParams
import com.appCraft.domain.model.TvShowModel
import kotlinx.coroutines.Dispatchers

class SaveTvShowUseCase(
    private val filmsGateway: FilmsGateway
) : UseCaseWithParams<TvShowModel.TvShowX, Unit>(Dispatchers.IO) {
    override suspend fun execute(parameters: TvShowModel.TvShowX): Unit =
        filmsGateway.addTvShowToDB(parameters)
}