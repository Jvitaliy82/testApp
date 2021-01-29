package com.appcraft.domain.interactor.auth

import com.appcraft.domain.gateway.AuthGateway
import com.appcraft.domain.global.interactor.UseCase
import kotlinx.coroutines.Dispatchers

class GetAuthorizationTokenUseCase(
    private val authGateway: AuthGateway
) : UseCase<String>(Dispatchers.IO) {
    override suspend fun execute() =
        authGateway.getAuthorizationToken()
}