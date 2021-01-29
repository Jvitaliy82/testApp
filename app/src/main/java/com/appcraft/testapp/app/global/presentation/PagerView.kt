package com.appcraft.testapp.app.global.presentation

import moxy.viewstate.strategy.alias.AddToEndSingle

interface PagerView : NavigationMvpView {
    @AddToEndSingle
    fun showData(show: Boolean)

    @AddToEndSingle
    fun showRefreshProgress(show: Boolean)

    @AddToEndSingle
    fun showEmptyProgress(show: Boolean)

    @AddToEndSingle
    fun showPageProgress(show: Boolean)

    @AddToEndSingle
    fun showEmptyView(show: Boolean)

    @AddToEndSingle
    fun showErrorView(show: Boolean, message: String)
}
