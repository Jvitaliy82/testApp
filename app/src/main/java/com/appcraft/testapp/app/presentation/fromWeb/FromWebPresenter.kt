package com.appcraft.testapp.app.presentation.fromWeb

import android.util.Log
import com.appcraft.domain.global.CoroutineProvider
import com.appcraft.domain.interactor.films.AddTvShowMPUseCase
import com.appcraft.domain.interactor.films.GetTvShowByPageUseCase
import com.appcraft.domain.model.TvShowItemMP
import com.appcraft.testapp.app.Screens
import com.appcraft.testapp.app.dispatcher.event.CustomEvent
import com.appcraft.testapp.app.dispatcher.event.EventDispatcher
import com.appcraft.testapp.app.dispatcher.notifier.Notifier
import com.appcraft.testapp.app.global.presentation.BasePresenter
import com.appcraft.testapp.app.global.presentation.ErrorHandler
import kotlinx.coroutines.launch
import moxy.InjectViewState
import org.koin.core.component.inject

@InjectViewState
class FromWebPresenter : BasePresenter<FromWebView>(), EventDispatcher.EventListener {
    private val eventDispatcher: EventDispatcher by inject()
    private val coroutineProvider: CoroutineProvider by inject()
    private val errorHandler: ErrorHandler by inject()
    private val notifier: Notifier by inject()
    private val getTvShowByPageUseCase: GetTvShowByPageUseCase by inject()
    private val addTvShowMPUseCase: AddTvShowMPUseCase by inject()

    init {
        subscribeToEvents()
    }

    fun getTvShow(page: Int) {
        coroutineProvider.scopeMain.launch {
            getTvShowByPageUseCase(page).process(
                { result ->
                    result.tv_showModels?.let {
                        Log.d("M1", it.toString())
                        viewState.setListInAdapter(it)
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

    fun saveTvShow(tvShowModel: TvShowItemMP) {
        coroutineProvider.scopeMain.launch {
            addTvShowMPUseCase(tvShowModel).process(
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

    fun navigateToDetailFragment(id: Long) {
        viewState.routerForwardTo(Screens.Flow.detail(id))
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


//    private fun tryContinue() {
//        if (databaseInitialized && animationFinished) {
//            viewState.routerNewRootScreen(Screens.Flow.main())
//        }
//    }
}