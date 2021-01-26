package com.appCraft.testApp.global.presentation

import com.github.terrakok.cicerone.androidx.AppScreen
import moxy.MvpView
import moxy.viewstate.strategy.alias.OneExecution

interface NavigationMvpView : MvpView {
    @OneExecution
    fun routerNavigateTo(screen: AppScreen)

    @OneExecution
    fun routerNewRootScreen(screen: AppScreen)

    @OneExecution
    fun routerNewRootChain(vararg screens: AppScreen)

    @OneExecution
    fun routerExit()

    @OneExecution
    fun routerStartFlow(flow: AppScreen)

    @OneExecution
    fun routerReplaceScreen(flow: AppScreen)

    @OneExecution
    fun routerFinishFlow()

    @OneExecution
    fun routerBackTo(flow: AppScreen)

    @OneExecution
    fun routerForwardTo(flow: AppScreen)

    @OneExecution
    fun routerToTop(flow: AppScreen)
}
