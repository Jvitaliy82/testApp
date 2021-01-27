package com.appCraft.testApp.presentation.detail

import com.appCraft.domain.global.CoroutineProvider
import com.appCraft.domain.interactor.films.GetTvDetailByIdUseCase
import com.appCraft.testApp.dispatcher.event.CustomEvent
import com.appCraft.testApp.dispatcher.event.EventDispatcher
import com.appCraft.testApp.dispatcher.notifier.Notifier
import com.appCraft.testApp.global.presentation.BasePresenter
import com.appCraft.testApp.global.presentation.ErrorHandler
import kotlinx.coroutines.launch
import moxy.InjectViewState
import org.koin.core.component.inject

@InjectViewState
class DetailPresenter : BasePresenter<DetailView>(), EventDispatcher.EventListener {
    private val eventDispatcher: EventDispatcher by inject()
    private val coroutineProvider: CoroutineProvider by inject()
    private val errorHandler: ErrorHandler by inject()
    private val notifier: Notifier by inject()
    private val getTvDetailByIdUseCase: GetTvDetailByIdUseCase by inject()

    var id: String = ""

    init {
        subscribeToEvents()
    }


    fun getTvDetail() {
        coroutineProvider.scopeMain.launch {
            getTvDetailByIdUseCase(id).process(
                { result ->
                    result?.let {
                        viewState.setData(it)
                    }
                },
                { error ->
                    errorHandler.proceed(error) {
                        notifier.sendMessage(it)
                    }
                }
            )
        }
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


}