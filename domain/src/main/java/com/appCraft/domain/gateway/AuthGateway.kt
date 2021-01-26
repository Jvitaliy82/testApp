package com.appCraft.domain.gateway

interface AuthGateway {
    suspend fun getAuthorizationToken(): String
}