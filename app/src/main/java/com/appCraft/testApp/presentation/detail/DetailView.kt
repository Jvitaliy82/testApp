package com.appCraft.testApp.presentation.detail

import com.appCraft.domain.model.TvDetailModel
import com.appCraft.testApp.global.presentation.NavigationMvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface DetailView : NavigationMvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setData(tvDetail: TvDetailModel)

}