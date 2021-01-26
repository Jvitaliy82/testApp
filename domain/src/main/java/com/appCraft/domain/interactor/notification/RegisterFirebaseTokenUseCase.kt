package com.appCraft.domain.interactor.notification

import com.appCraft.domain.gateway.NotificationGateway
import com.appCraft.domain.global.interactor.UseCaseWithParams
import kotlinx.coroutines.Dispatchers

class RegisterFirebaseTokenUseCase(
    private val notificationGateway: NotificationGateway
) : UseCaseWithParams<String, Unit>(Dispatchers.IO) {
    override suspend fun execute(parameters: String) =
        notificationGateway.registerNotificationToken(parameters)
}