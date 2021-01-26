package com.appCraft.data.gateway

import com.appCraft.data.network.CommonApi
import com.appCraft.domain.gateway.AuthGateway

class AuthGatewayImpl(
    private val commonApi: CommonApi
) : AuthGateway {
    override suspend fun getAuthorizationToken(): String {
        commonApi.getSampleData(0.0).lat.toString()
        TODO("STUB")
    }
}