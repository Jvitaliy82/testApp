package com.appcraft.data.gateway

import com.appcraft.data.network.AuthApi
import com.appcraft.data.network.model.FirebaseTokenPairModel
import com.appcraft.data.preference.Preferences
import com.appcraft.domain.gateway.NotificationGateway

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