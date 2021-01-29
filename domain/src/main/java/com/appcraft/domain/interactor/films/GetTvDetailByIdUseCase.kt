package com.appcraft.domain.interactor.films

import com.appcraft.domain.gateway.FilmsGateway
import com.appcraft.domain.global.interactor.UseCaseWithParams
import com.appcraft.domain.model.TvDetailModel
import kotlinx.coroutines.Dispatchers

class GetTvDetailByIdUseCase(
    private val filmsGateway: FilmsGateway
) : UseCaseWithParams<Int, TvDetailModel>(Dispatchers.IO) {
    override suspend fun execute(id: Int) : TvDetailModel =
        filmsGateway.getDetailById(id)
}