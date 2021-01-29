package com.appcraft.testapp.presentation.fromWeb

import android.util.Log
import com.appcraft.domain.global.CoroutineProvider
import com.appcraft.domain.interactor.films.GetTvShowByPageUseCase
import com.appcraft.domain.model.TvShowModel
import com.appcraft.testapp.Screens
import com.appcraft.testapp.dispatcher.event.CustomEvent
import com.appcraft.testapp.dispatcher.event.EventDispatcher
import com.appcraft.testapp.dispatcher.notifier.Notifier
import com.appcraft.testapp.global.presentation.BasePresenter
import com.appcraft.testapp.global.presentation.ErrorHandler
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

    init {
        subscribeToEvents()
    }

    fun getTvShow(page: Int) {
        coroutineProvider.scopeMain.launch {
            getTvShowByPageUseCase(page).process(
                { result ->
                    result.tv_shows?.let {
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

    fun navigateToDetailFragment(id: Int) {
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

    fun saveTvShow(tvShow: TvShowModel.TvShowX) {
        
    }


//    private fun tryContinue() {
//        if (databaseInitialized && animationFinished) {
//            viewState.routerNewRootScreen(Screens.Flow.main())
//        }
//    }
}