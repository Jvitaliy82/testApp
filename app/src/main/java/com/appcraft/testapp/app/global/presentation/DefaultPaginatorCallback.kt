package com.appcraft.testapp.app.global.presentation

import com.appcraft.testapp.app.dispatcher.notifier.Notifier
import com.appcraft.testapp.app.dispatcher.notifier.SystemMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import pro.appcraft.lib.pagination.BasePaginator

class DefaultPaginatorCallback<T>(
    private val presenter: BasePresenter<out PagerView>,
    private val errorHandler: ErrorHandler,
    private val notifier: Notifier,
    private val coroutineUiScope: CoroutineScope,
    private val populateDataCallback: (List<T>) -> Unit
) : BasePaginator.ViewController<T> {
    override fun showEmptyProgress(show: Boolean) {
        coroutineUiScope.launch {
            presenter.viewState.showEmptyProgress(show)
        }
    }

    override fun showEmptyError(show: Boolean, error: Throwable?) {
        coroutineUiScope.launch {
            error?.let {
                errorHandler.proceed(error) { presenter.viewState.showErrorView(show, it) }
            } ?: run {
                presenter.viewState.showErrorView(show, "")
            }
        }
    }

    override fun showEmptyView(show: Boolean) {
        coroutineUiScope.launch {
            presenter.viewState.showEmptyView(show)
        }
    }

    override fun showData(show: Boolean, data: List<T>) {
        coroutineUiScope.launch {
            presenter.viewState.showData(show)
            populateDataCallback.invoke(data)
        }
    }

    override fun showErrorMessage(error: Throwable) {
        coroutineUiScope.launch {
            errorHandler.proceed(error) { notifier.sendMessage(it, SystemMessage.Level.ERROR) }
        }
    }

    override fun showRefreshProgress(show: Boolean) {
        coroutineUiScope.launch {
            presenter.viewState.showRefreshProgress(show)
        }
    }

    override fun showPageProgress(show: Boolean) {
        coroutineUiScope.launch {
            presenter.viewState.showPageProgress(show)
        }
    }
}
