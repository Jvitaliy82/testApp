package com.appcraft.testapp.app

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.appcraft.data.preference.Preferences
import com.appcraft.testapp.app.db.ObjectBoxBrowser
import com.appcraft.testapp.app.di.appComponent
import com.appcraft.testapp.app.observer.AppLifecycleObserver
import com.google.firebase.FirebaseApp
import com.yariksoffice.lingver.Lingver
import io.objectbox.BoxStore
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.KoinExperimentalAPI
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    private val appLifecycleObserver: AppLifecycleObserver by inject()
    private val boxStore: BoxStore by inject()
    private val preferences: Preferences by inject()

    override fun onCreate() {
        super.onCreate()
//        initWebView()
        initFirebase()
        initKoin()
        initLifecycleObserver()
        initObjectBoxBrowser()
        initLanguage()
    }

//    private fun initWebView() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//            val processName = getProcessName()
//            if (packageName != processName) {
//                WebView.setDataDirectorySuffix(processName)
//            }
//        }
//    }

    private fun initFirebase() {
        FirebaseApp.initializeApp(this)
    }

    @OptIn(KoinExperimentalAPI::class)
    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            androidLogger(Level.DEBUG)
            modules(appComponent)
            fragmentFactory()
        }
    }

    private fun initLifecycleObserver() {
        ProcessLifecycleOwner
            .get()
            .lifecycle
            .addObserver(appLifecycleObserver)
    }

    private fun initLanguage() {
        Lingver.init(this, preferences.language.get())
    }

    private fun initObjectBoxBrowser() {
        ObjectBoxBrowser().init(boxStore, this)
    }
}