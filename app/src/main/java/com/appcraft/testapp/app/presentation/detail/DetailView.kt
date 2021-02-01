package com.appcraft.testapp.app.presentation.detail

import com.appcraft.domain.model.TvDetail
import com.appcraft.testapp.app.global.presentation.NavigationMvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface DetailView : NavigationMvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setData(tvDetail: TvDetail)

}