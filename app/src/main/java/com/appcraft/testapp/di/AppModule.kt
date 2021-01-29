package com.appcraft.testapp.di

import com.appcraft.device.manager.NotificationMessageManager
import com.appcraft.domain.global.CoroutineProvider
import com.appcraft.testapp.dispatcher.event.EventDispatcher
import com.appcraft.testapp.dispatcher.notifier.Notifier
import com.appcraft.testapp.global.presentation.ErrorHandler
import com.appcraft.testapp.observer.AppLifecycleObserver
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