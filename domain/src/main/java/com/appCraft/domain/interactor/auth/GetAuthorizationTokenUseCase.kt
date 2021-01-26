package com.appCraft.domain.interactor.auth

import com.appCraft.domain.gateway.AuthGateway
import com.appCraft.domain.global.interactor.UseCase
import kotlinx.coroutines.Dispatchers

class GetAuthorizationTokenUseCase(
    private val authGateway: AuthGateway
) : UseCase<String>(Dispatchers.IO) {
    override suspend fun execute() =
        authGateway.getAuthorizationToken()
}