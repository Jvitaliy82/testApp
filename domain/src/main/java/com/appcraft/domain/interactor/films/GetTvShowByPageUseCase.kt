package com.appcraft.domain.interactor.films

import com.appcraft.domain.gateway.FilmsGateway
import com.appcraft.domain.global.interactor.UseCaseWithParams
import com.appcraft.domain.model.TvShowMostPopular
import kotlinx.coroutines.Dispatchers

class GetTvShowByPageUseCase(
    private val filmsGateway: FilmsGateway
) : UseCaseWithParams<Int, TvShowMostPopular>(Dispatchers.IO) {
    override suspend fun execute(page: Int) : TvShowMostPopular =
        filmsGateway.getFilmByPage(page)
}