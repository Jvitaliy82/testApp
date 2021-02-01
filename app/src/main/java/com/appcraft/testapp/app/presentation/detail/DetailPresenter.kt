package com.appcraft.testapp.app.presentation.detail

import android.util.Log
import com.appcraft.domain.global.CoroutineProvider
import com.appcraft.domain.interactor.films.AddTvShowMPUseCase
import com.appcraft.domain.interactor.films.GetTvDetailByIdUseCase
import com.appcraft.domain.model.TvShowItemMP
import com.appcraft.testapp.app.dispatcher.event.CustomEvent
import com.appcraft.testapp.app.dispatcher.event.EventDispatcher
import com.appcraft.testapp.app.dispatcher.notifier.Notifier
import com.appcraft.testapp.app.global.presentation.BasePresenter
import com.appcraft.testapp.app.global.presentation.ErrorHandler
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
    private val addTvShowMPUseCase: AddTvShowMPUseCase by inject()

    var currentItem: TvShowItemMP? = null

    init {
        subscribeToEvents()
    }


    fun getTvDetail() {
        currentItem?.let {
            coroutineProvider.scopeMain.launch {
                getTvDetailByIdUseCase(it.uuid).process(
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

    }

    fun saveCurrent() {
        currentItem?.let { item ->
            coroutineProvider.scopeMain.launch {
                addTvShowMPUseCase(item).process(
                    //todo
                    {
                        Log.d("M1", "успешно сохранили!!!")
                    },
                    {
                        Log.d("M1", "что то не так: ${it.message}")
                    }
                )
            }
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