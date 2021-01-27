package com.appCraft.testApp.di

import com.appCraft.data.gateway.AuthGatewayImpl
import com.appCraft.data.gateway.FilmGatewayImpl
import com.appCraft.data.gateway.NotificationGatewayImpl
import com.appCraft.domain.gateway.AuthGateway
import com.appCraft.domain.gateway.FilmsGateway
import com.appCraft.domain.gateway.NotificationGateway
import org.koin.dsl.module

internal val gatewayModule = module {
    single<AuthGateway> { AuthGatewayImpl(get()) }
    single<FilmsGateway> { FilmGatewayImpl(get()) }
    single<NotificationGateway> { NotificationGatewayImpl(get(), get()) }
}