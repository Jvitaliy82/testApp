package com.appcraft.domain.interactor.films

import com.appcraft.domain.gateway.FilmsGateway
import com.appcraft.domain.global.interactor.UseCaseWithParams
import com.appcraft.domain.model.TvDetail
import kotlinx.coroutines.Dispatchers

class GetTvDetailByIdUseCase(
    private val filmsGateway: FilmsGateway
) : UseCaseWithParams<Int, TvDetail>(Dispatchers.IO) {
    override suspend fun execute(id: Int) : TvDetail =
        filmsGateway.getDetailById(id)
}