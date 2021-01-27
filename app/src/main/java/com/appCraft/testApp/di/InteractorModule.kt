package com.appCraft.testApp.di

import com.appCraft.domain.interactor.auth.GetAuthorizationTokenUseCase
import com.appCraft.domain.interactor.films.GetTvDetailByIdUseCase
import com.appCraft.domain.interactor.films.GetTvShowByPageUseCase
import com.appCraft.domain.interactor.notification.RegisterFirebaseTokenUseCase
import org.koin.dsl.module

internal val interactorModule = module {
    single { GetAuthorizationTokenUseCase(get()) }

    single { RegisterFirebaseTokenUseCase(get()) }

    single { GetTvShowByPageUseCase(get()) }
    single { GetTvDetailByIdUseCase(get()) }

}