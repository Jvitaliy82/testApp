package com.appcraft.testapp.app.presentation.fromWeb

import android.util.Log
import com.appcraft.domain.global.CoroutineProvider
import com.appcraft.domain.interactor.films.AddTvShowMPUseCase
import com.appcraft.domain.interactor.films.DeleteItemUseCase
import com.appcraft.domain.interactor.films.GetAllTvShowMPUseCase
import com.appcraft.domain.interactor.films.GetTvShowByPageUseCase
import com.appcraft.domain.model.TvShowItemMP
import com.appcraft.testapp.R
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
    private val deleteItemUseCase: DeleteItemUseCase by inject()
    private val getAllTvShowMPUseCase: GetAllTvShowMPUseCase by inject()

    init {
        subscribeToEvents()
    }

    fun getTvShow(page: Int) {
        coroutineProvider.scopeMain.launch {
            getTvShowByPageUseCase(page).process(
                { result ->
                    result.tvShowModels?.let {
                        markSaved(it)
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

    fun markSaved(list: List<TvShowItemMP>) {
        coroutineProvider.scopeMain.launch {
            getAllTvShowMPUseCase().process(
                { result ->
                    list.forEach {
                        if (result.contains(it)) {
                            it.isFavorite = true
                        }
                    }
                    viewState.setListInAdapter(list)

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
                {
                    notifier.sendMessage(R.string.item_saved)
                },
                {
                    Log.d("M1", "что то не так: ${it.message}")
                }
            )
        }

    }

    fun deleteTvShow(tvItem: TvShowItemMP) {
        coroutineProvider.scopeMain.launch {
            deleteItemUseCase(tvItem).process(
                {
                    notifier.sendMessage(R.string.item_deleted)
                },
                {
                    Log.d("M1", "что то не так: ${it.message}")
                }
            )
        }
    }


    fun navigateToDetailFragment(item: TvShowItemMP) {
        viewState.routerForwardTo(Screens.Flow.detail(item))
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
            else -> {
            }
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