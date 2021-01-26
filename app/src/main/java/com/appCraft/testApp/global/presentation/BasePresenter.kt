package com.appCraft.testApp.global.presentation

import moxy.MvpPresenter
import moxy.MvpView
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent

@OptIn(KoinApiExtension::class)
open class BasePresenter<View : MvpView> : MvpPresenter<View>(), KoinComponent
