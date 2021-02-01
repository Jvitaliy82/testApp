package com.appcraft.testapp.app.di

import com.appcraft.domain.interactor.auth.GetAuthorizationTokenUseCase
import com.appcraft.domain.interactor.films.AddTvShowMPUseCase
import com.appcraft.domain.interactor.films.GetAllTvShowMPUseCase
import com.appcraft.domain.interactor.films.GetTvDetailByIdUseCase
import com.appcraft.domain.interactor.films.GetTvShowByPageUseCase
import com.appcraft.domain.interactor.notification.RegisterFirebaseTokenUseCase
import org.koin.dsl.module

internal val interactorModule = module {
    single { GetAuthorizationTokenUseCase(get()) }

    single { RegisterFirebaseTokenUseCase(get()) }

    single { GetTvShowByPageUseCase(get()) }
    single { GetTvDetailByIdUseCase(get()) }
    single { AddTvShowMPUseCase(get()) }
    single { GetAllTvShowMPUseCase(get()) }

}