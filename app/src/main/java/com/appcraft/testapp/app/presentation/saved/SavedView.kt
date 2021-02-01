package com.appcraft.testapp.app.presentation.saved

import com.appcraft.domain.model.TvShowItemMP
import com.appcraft.testapp.app.global.presentation.NavigationMvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface SavedView : NavigationMvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setListInAdapter(list: List<TvShowItemMP>)
}