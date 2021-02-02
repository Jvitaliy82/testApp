package com.appcraft.testapp.app.presentation.detail

import com.appcraft.domain.model.TvDetailItem
import com.appcraft.testapp.app.global.presentation.NavigationMvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface DetailView : NavigationMvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setData(tvDetailItem: TvDetailItem)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun visibleSaveButton(isVisible: Boolean)
}