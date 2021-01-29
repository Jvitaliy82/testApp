package com.appcraft.testapp.app.presentation.main

import com.appcraft.domain.global.CoroutineProvider
import com.appcraft.testapp.app.Screens
import com.appcraft.testapp.app.dispatcher.event.CustomEvent
import com.appcraft.testapp.app.dispatcher.event.EventDispatcher
import com.appcraft.testapp.app.dispatcher.notifier.Notifier
import com.appcraft.testapp.app.global.presentation.BasePresenter
import com.appcraft.testapp.app.global.presentation.ErrorHandler
import com.appcraft.testapp.app.utils.MainTab
import moxy.InjectViewState
import org.koin.core.component.inject

@InjectViewState
class MainPresenter : BasePresenter<MainView>(), EventDispatcher.EventListener {
    private val eventDispatcher: EventDispatcher by inject()
    private val coroutineProvider: CoroutineProvider by inject()
    private val errorHandler: ErrorHandler by inject()
    private val notifier: Notifier by inject()

    private var animationFinished: Boolean = false
    private var databaseInitialized: Boolean = false

    var selectedNavigationTab: MainTab = MainTab.WEB

    init {
        subscribeToEvents()
        initDB()
    }

    override fun onDestroy() {
        eventDispatcher.removeEventListener(this)
        super.onDestroy()
    }

    override fun onEvent(event: CustomEvent) {
        when (event) {
            is CustomEvent.SampleEvent -> {
                // Can use info from event.field directly without type cast:
                val x = event.longData
                val y = event.stringData
            }
            else -> { }
        }
    }

    private fun subscribeToEvents() {
        eventDispatcher.addEventListener(CustomEvent.SampleEvent::class, this)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    private fun onError(t: Throwable) {
        // viewState.hideProgress()
        errorHandler.proceed(t, false, notifier::sendAlert)
    }

    private fun initDB() {
        // TODO DB init usecase
        databaseInitialized = true
        tryContinue()
    }

    fun onAnimationEnd() {
        animationFinished = true
        tryContinue()
    }

    private fun tryContinue() {
        if (databaseInitialized && animationFinished) {
            viewState.routerNewRootScreen(Screens.Flow.main())
        }
    }
}