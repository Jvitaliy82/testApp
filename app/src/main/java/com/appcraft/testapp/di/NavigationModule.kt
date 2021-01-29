package com.appcraft.testapp.di

import com.github.terrakok.cicerone.Cicerone
import org.koin.dsl.module
import pro.appcraft.lib.navigation.AppRouter

internal val navigationModule = module {
    val cicerone: Cicerone<AppRouter> = Cicerone.create(AppRouter())
    single { cicerone.router as AppRouter }
    single { cicerone.getNavigatorHolder() }
}