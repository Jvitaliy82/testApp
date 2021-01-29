package com.appcraft.testapp.app.presentation.fromWeb

import com.appcraft.domain.model.TvShowModel
import com.appcraft.testapp.app.global.presentation.NavigationMvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface FromWebView : NavigationMvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setListInAdapter(list: List<TvShowModel.TvShowX>)
}