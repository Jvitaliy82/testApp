package com.appcraft.domain.gateway

interface AuthGateway {
    suspend fun getAuthorizationToken(): String
}