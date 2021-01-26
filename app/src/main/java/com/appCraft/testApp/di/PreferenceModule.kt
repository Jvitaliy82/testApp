package com.appCraft.testApp.di

import androidx.preference.PreferenceManager
import com.appCraft.data.preference.Preferences
import com.tfcporciuncula.flow.FlowSharedPreferences
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

@OptIn(ExperimentalCoroutinesApi::class)
internal val preferenceModule = module {
    single { Preferences(get()) }
    single { FlowSharedPreferences(get()) }
    factory { PreferenceManager.getDefaultSharedPreferences(androidContext()) }
}