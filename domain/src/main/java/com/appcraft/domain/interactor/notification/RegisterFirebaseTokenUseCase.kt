package com.appcraft.domain.interactor.notification

import com.appcraft.domain.gateway.NotificationGateway
import com.appcraft.domain.global.interactor.UseCaseWithParams
import kotlinx.coroutines.Dispatchers

class RegisterFirebaseTokenUseCase(
    private val notificationGateway: NotificationGateway
) : UseCaseWithParams<String, Unit>(Dispatchers.IO) {
    override suspend fun execute(parameters: String) =
        notificationGateway.registerNotificationToken(parameters)
}