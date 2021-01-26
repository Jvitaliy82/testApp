package com.appCraft.data.gateway

import com.appCraft.data.network.AuthApi
import com.appCraft.data.network.model.FirebaseTokenPairModel
import com.appCraft.data.preference.Preferences
import com.appCraft.domain.gateway.NotificationGateway

class NotificationGatewayImpl(
    private val authApi: AuthApi,
    private val preferences: Preferences
) : NotificationGateway {
    override suspend fun registerNotificationToken(newToken: String) {
        val tokenPair = FirebaseTokenPairModel(
            oldToken = preferences.firebaseToken.get(),
            newToken = newToken
        )
        preferences.firebaseToken.set(newToken)

        TODO("STUB")
    }
}