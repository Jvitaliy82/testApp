package com.appcraft.testapp.presentation.detail

import com.appcraft.domain.global.CoroutineProvider
import com.appcraft.domain.interactor.films.GetTvDetailByIdUseCase
import com.appcraft.testapp.dispatcher.event.CustomEvent
import com.appcraft.testapp.dispatcher.event.EventDispatcher
import com.appcraft.testapp.dispatcher.notifier.Notifier
import com.appcraft.testapp.global.presentation.BasePresenter
import com.appcraft.testapp.global.presentation.ErrorHandler
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

    var id: Int = 0

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