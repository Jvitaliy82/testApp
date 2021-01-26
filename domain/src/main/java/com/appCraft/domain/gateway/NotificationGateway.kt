package com.appCraft.domain.gateway

interface NotificationGateway {
    suspend fun registerNotificationToken(newToken: String)
}