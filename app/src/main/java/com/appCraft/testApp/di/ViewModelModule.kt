package com.appCraft.testApp.di

import pro.appcraft.lib.navigation.FlowNavigationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {
    viewModel { FlowNavigationViewModel(get()) }
}