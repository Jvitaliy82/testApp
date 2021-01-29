package com.appcraft.testapp.observer

import android.content.Context
import android.content.IntentFilter
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.appcraft.testapp.receiver.NotificationReceiver

class AppLifecycleObserver(private val context: Context) : LifecycleObserver {
    /**
     * When app is started
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onAppStart() {
        context.registerReceiver(
            NotificationReceiver(),
            IntentFilter(NotificationReceiver.BROADCAST_RECEIVER)
        )
    }

    /**
     * When app enters foreground
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onEnterForeground() {
    }

    /**
     * When app enters background
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onEnterBackground() {
    }

    /**
     * When app surely enters foreground
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
    }
}