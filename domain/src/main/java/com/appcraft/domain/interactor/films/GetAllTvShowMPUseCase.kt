package com.appcraft.domain.interactor.films

import com.appcraft.domain.gateway.FilmsGateway
import com.appcraft.domain.global.interactor.UseCase
import com.appcraft.domain.model.TvShowItemMP
import kotlinx.coroutines.Dispatchers

class GetAllTvShowMPUseCase(
    private val filmsGateway: FilmsGateway
) : UseCase<List<TvShowItemMP>>(Dispatchers.IO) {
    override suspend fun execute() =
        filmsGateway.getAllTvShowFromDB()
}