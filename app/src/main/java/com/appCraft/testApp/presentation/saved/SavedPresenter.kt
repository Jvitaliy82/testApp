package com.appCraft.testApp.presentation.saved

import com.appCraft.domain.global.CoroutineProvider
import com.appCraft.testApp.dispatcher.event.CustomEvent
import com.appCraft.testApp.dispatcher.event.EventDispatcher
import com.appCraft.testApp.dispatcher.notifier.Notifier
import com.appCraft.testApp.global.presentation.BasePresenter
import com.appCraft.testApp.global.presentation.ErrorHandler
import moxy.InjectViewState
import org.koin.core.component.inject

@InjectViewState
class SavedPresenter : BasePresenter<SavedView>(), EventDispatcher.EventListener {
    private val eventDispatcher: EventDispatcher by inject()
    private val coroutineProvider: CoroutineProvider by inject()
    private val errorHandler: ErrorHandler by inject()
    private val notifier: Notifier by inject()


    init {
        subscribeToEvents()
    }

//    fun getTvShow(page: Int) {
//        coroutineProvider.scopeMain.launch {
//            getTvShowByPageUseCase(page).process(
//                { result ->
//                    result.tv_shows?.let {
//                        Log.d("M1", it.toString())
//                        viewState.setListInAdapter(it)
//                    }
//                },
//                { error ->
//                    errorHandler.proceed(error) {
//                        notifier.sendMessage(it)
//                    }
//                }
//            )
//        }
//    }

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


//    private fun tryContinue() {
//        if (databaseInitialized && animationFinished) {
//            viewState.routerNewRootScreen(Screens.Flow.main())
//        }
//    }
}