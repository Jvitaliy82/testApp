package com.appcraft.testapp.app.di

import com.appcraft.data.gateway.AuthGatewayImpl
import com.appcraft.data.gateway.FilmGatewayImpl
import com.appcraft.data.gateway.NotificationGatewayImpl
import com.appcraft.domain.gateway.AuthGateway
import com.appcraft.domain.gateway.FilmsGateway
import com.appcraft.domain.gateway.NotificationGateway
import org.koin.dsl.module

internal val gatewayModule = module {
    single<AuthGateway> { AuthGatewayImpl(get()) }
    single<FilmsGateway> { FilmGatewayImpl(get(), get()) }
    single<NotificationGateway> { NotificationGatewayImpl(get(), get()) }
}