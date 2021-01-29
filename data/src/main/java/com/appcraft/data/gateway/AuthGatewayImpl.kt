package com.appcraft.data.gateway

import com.appcraft.data.network.CommonApi
import com.appcraft.domain.gateway.AuthGateway

class AuthGatewayImpl(
    private val commonApi: CommonApi
) : AuthGateway {
    override suspend fun getAuthorizationToken(): String {
        commonApi.getSampleData(0.0).lat.toString()
        TODO("STUB")
    }
}