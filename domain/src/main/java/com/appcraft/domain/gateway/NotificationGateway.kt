package com.appcraft.domain.gateway

interface NotificationGateway {
    suspend fun registerNotificationToken(newToken: String)
}