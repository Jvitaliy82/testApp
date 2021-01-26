package com.appCraft.testApp.di

import pro.appcraft.lib.navigation.AppRouter
import com.github.terrakok.cicerone.Cicerone
import org.koin.dsl.module

internal val navigationModule = module {
    val cicerone: Cicerone<AppRouter> = Cicerone.create(AppRouter())
    single { cicerone.router as AppRouter }
    single { cicerone.getNavigatorHolder() }
}