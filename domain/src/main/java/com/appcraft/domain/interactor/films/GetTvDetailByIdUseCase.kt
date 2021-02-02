package com.appcraft.domain.interactor.films

import com.appcraft.domain.gateway.FilmsGateway
import com.appcraft.domain.global.interactor.UseCaseWithParams
import com.appcraft.domain.model.TvDetailItem
import kotlinx.coroutines.Dispatchers

class GetTvDetailByIdUseCase(
    private val filmsGateway: FilmsGateway
) : UseCaseWithParams<Long, TvDetailItem>(Dispatchers.IO) {
    override suspend fun execute(id: Long) : TvDetailItem =
        filmsGateway.getDetailById(id)
}