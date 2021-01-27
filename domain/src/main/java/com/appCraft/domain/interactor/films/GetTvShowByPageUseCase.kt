package com.appCraft.domain.interactor.films

import com.appCraft.domain.gateway.FilmsGateway
import com.appCraft.domain.global.interactor.UseCaseWithParams
import com.appCraft.domain.model.TvShowModel
import kotlinx.coroutines.Dispatchers

class GetTvShowByPageUseCase(
    private val filmsGateway: FilmsGateway
) : UseCaseWithParams<Int, TvShowModel>(Dispatchers.IO) {
    override suspend fun execute(page: Int) : TvShowModel =
        filmsGateway.getFilmByPage(page)
}