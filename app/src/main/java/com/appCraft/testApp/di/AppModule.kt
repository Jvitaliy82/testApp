package com.appCraft.testApp.di

import com.appCraft.testApp.observer.AppLifecycleObserver
import com.appCraft.testApp.dispatcher.event.EventDispatcher
import com.appCraft.testApp.dispatcher.notifier.Notifier
import com.appCraft.testApp.global.presentation.ErrorHandler
import com.appCraft.device.manager.NotificationMessageManager
import com.appCraft.domain.global.CoroutineProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import pro.appcraft.lib.permissions.StoragePermissionHandler

internal val appModule = module {
    factory { androidContext().resources }

    factory { AppLifecycleObserver(get()) }

    single { Notifier(get()) }
    single { ErrorHandler(get(), get()) }
    single { EventDispatcher() }
    single { CoroutineProvider() }
    single { NotificationMessageManager(androidContext()) }
    single { StoragePermissionHandler(androidContext()) }
}