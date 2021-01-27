package com.appCraft.testApp.presentation.fromWeb

import com.appCraft.domain.model.TvShowModel
import com.appCraft.testApp.global.presentation.NavigationMvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface FromWebView : NavigationMvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setListInAdapter(list: List<TvShowModel.TvShowX>)
}