package com.appCraft.testApp.presentation.main

import com.appCraft.domain.global.CoroutineProvider
import com.appCraft.testApp.Screens
import com.appCraft.testApp.dispatcher.event.CustomEvent
import com.appCraft.testApp.dispatcher.event.EventDispatcher
import com.appCraft.testApp.dispatcher.notifier.Notifier
import com.appCraft.testApp.global.presentation.BasePresenter
import com.appCraft.testApp.global.presentation.ErrorHandler
import com.appCraft.testApp.utils.MainTab
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