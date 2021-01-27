package com.appCraft.domain.interactor.films

import com.appCraft.domain.gateway.FilmsGateway
import com.appCraft.domain.global.interactor.UseCaseWithParams
import com.appCraft.domain.model.TvDetailModel
import kotlinx.coroutines.Dispatchers

class GetTvDetailByIdUseCase(
    private val filmsGateway: FilmsGateway
) : UseCaseWithParams<String, TvDetailModel>(Dispatchers.IO) {
    override suspend fun execute(id: String) : TvDetailModel =
        filmsGateway.getDetailById(id)
}