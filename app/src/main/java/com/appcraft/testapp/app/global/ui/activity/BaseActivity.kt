package com.appcraft.testapp.app.global.ui.activity

import android.content.Context
import android.os.Bundle
import com.appcraft.data.preference.Preferences
import com.appcraft.domain.model.enums.Language
import com.appcraft.testapp.R
import com.appcraft.testapp.app.global.presentation.ErrorHandler
import com.appcraft.testapp.app.global.ui.fragment.BaseFragment
import com.yariksoffice.lingver.Lingver
import moxy.MvpAppCompatActivity
import org.koin.android.ext.android.inject
import java.util.*

abstract class BaseActivity : MvpAppCompatActivity() {
    private val preferences: Preferences by inject()
    private val errorHandler: ErrorHandler by inject()

    private val currentFragment: BaseFragment?
        get() = supportFragmentManager.findFragmentById(R.id.container) as? BaseFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        setLanguage()
        super.onCreate(savedInstanceState)
    }

    @Suppress("DEPRECATION")
    private fun setLanguage() {
        if (!preferences.language.isSet()) {
            setDefaultLanguage()
        }

        Lingver.getInstance().setLocale(application, preferences.language.get())
        errorHandler.resources = baseContext.resources
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

        errorHandler.resources = baseContext.resources
    }

    private fun setDefaultLanguage() {
        preferences.language.set(
            Language[Locale.getDefault().language].tag
        )
    }

    override fun onBackPressed() = currentFragment?.onBackPressed() ?: super.onBackPressed()
}
