package com.appcraft.testapp.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pro.appcraft.lib.navigation.FlowNavigationViewModel

internal val viewModelModule = module {
    viewModel { FlowNavigationViewModel(get()) }
}