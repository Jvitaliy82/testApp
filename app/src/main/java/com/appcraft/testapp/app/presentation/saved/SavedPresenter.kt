package com.appcraft.testapp.app.presentation.saved

import com.appcraft.domain.global.CoroutineProvider
import com.appcraft.domain.interactor.films.DeleteItemUseCase
import com.appcraft.domain.interactor.films.GetAllTvShowMPUseCase
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
class SavedPresenter : BasePresenter<SavedView>(), EventDispatcher.EventListener {
    private val eventDispatcher: EventDispatcher by inject()
    private val coroutineProvider: CoroutineProvider by inject()
    private val errorHandler: ErrorHandler by inject()
    private val notifier: Notifier by inject()
    private val getAllTvShowMPUseCase: GetAllTvShowMPUseCase by inject()
    private val deleteItemUseCase: DeleteItemUseCase by inject()


    init {
        subscribeToEvents()
    }

    fun getAll() {
        coroutineProvider.scopeMain.launch {
            getAllTvShowMPUseCase().process({ result ->
                result.forEach {
                    it.isFavorite = true
                }
                viewState.setListInAdapter(result)
            }, {

            })
        }
    }

    fun deleteItem(tvShowItemMP: TvShowItemMP) {
        coroutineProvider.scopeMain.launch {
            deleteItemUseCase(tvShowItemMP).process({
                getAll()
            }, {

            })
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

    fun navigateToDetailFragment(item: TvShowItemMP) {
        viewState.routerForwardTo(Screens.Flow.detail(item))
    }




//    private fun tryContinue() {
//        if (databaseInitialized && animationFinished) {
//            viewState.routerNewRootScreen(Screens.Flow.main())
//        }
//    }
}