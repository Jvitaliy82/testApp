package com.appCraft.testApp.ui

import android.app.ActivityManager
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.appCraft.testApp.R
import com.appCraft.testApp.Screens
import com.appCraft.testApp.dispatcher.notifier.Notifier
import com.appCraft.testApp.dispatcher.notifier.SystemMessage
import com.appCraft.testApp.global.ui.activity.BaseActivity
import com.appCraft.testApp.global.ui.fragment.FlowFragment
import com.appCraft.testApp.presentation.app.AppPresenter
import com.appCraft.testApp.presentation.app.AppView
import com.appCraft.device.manager.PARAM_METADATA
import com.appCraft.device.manager.PARAM_TYPE
import com.appCraft.domain.global.CoroutineProvider
import com.appCraft.domain.model.enums.ContentType
import com.github.terrakok.cicerone.NavigatorHolder
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import moxy.presenter.InjectPresenter
import org.koin.android.ext.android.inject
import pro.appcraft.lib.navigation.AppNavigator
import pro.appcraft.lib.navigation.AppRouter
import pro.appcraft.lib.utils.common.addSystemWindowInsetToMargin
import pro.appcraft.lib.utils.dialog.AlertDialogAction
import pro.appcraft.lib.utils.dialog.AlertDialogType
import pro.appcraft.lib.utils.dialog.showAlertDialog

class AppActivity : BaseActivity(), AppView {
    @InjectPresenter
    lateinit var presenter: AppPresenter
    private val navigatorHolder: NavigatorHolder by inject()
    private val notifier: Notifier by inject()
    private val coroutineProvider: CoroutineProvider by inject()
    private val router: AppRouter by inject()

    private var notificationChannel: ReceiveChannel<SystemMessage>? = null

    private val navigator = object : AppNavigator(this, supportFragmentManager, R.id.container) {
        private var doubleBackToExitPressedOnce: Boolean = false

        override fun setupFragmentTransaction(
            fragmentTransaction: FragmentTransaction,
            currentFragment: Fragment?,
            nextFragment: Fragment?
        ) {
            //fix incorrect order lifecycle callback of MainFlowFragment
            fragmentTransaction.setReorderingAllowed(true)

            currentFragment?.exitTransition = null
            nextFragment?.enterTransition = null
        }

        override fun activityBack() {
            if (doubleBackToExitPressedOnce) {
                super.activityBack()
                return
            }
            doubleBackToExitPressedOnce = true
            notifier.sendMessage(R.string.double_back_to_exit)
            val exitDuration = resources.getInteger(R.integer.app_exit_duration)
            Handler(Looper.getMainLooper()).postDelayed({ doubleBackToExitPressedOnce = false }, exitDuration.toLong())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity)
        initWindowFlags()
        updateTaskDescription()

        if (savedInstanceState == null) {
            val externalIntentProcessed = processExternalIntent(
                type = intent.getStringExtra(PARAM_TYPE),
                metadata = intent.getStringExtra(PARAM_METADATA),
                fromApp = false
            )

            if (!externalIntentProcessed) {
                router.newRootScreen(Screens.Flow.splash())
            }
        } else {
            window.setBackgroundDrawableResource(R.color.colorBackground)
        }
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onStop() {
        unsubscribeOnSystemMessages()
        super.onStop()
    }

    override fun onStart() {
        super.onStart()
        subscribeOnSystemMessages()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onResume() {
        super.onResume()
        currentFocus?.clearFocus()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun subscribeOnSystemMessages() {
        notificationChannel = notifier.subscribe()
        coroutineProvider.scopeMainImmediate.launch {
            notificationChannel?.receiveAsFlow()?.collect(::onNextMessageNotify)
        }
    }

    private fun unsubscribeOnSystemMessages() {
        notificationChannel?.cancel()
        notificationChannel = null
    }

    private fun processExternalIntent(
        type: String?,
        metadata: String?,
        fromApp: Boolean
    ): Boolean {
        var processed = false
        val externalActionType = ContentType.getValueById(type?.toIntOrNull())
        val itemId = metadata?.toLongOrNull() ?: -1
        if ((externalActionType != ContentType.NONE) && (itemId >= 0)) {
            if (fromApp) {
                when (externalActionType) {
                    else -> { }
                }
            } else {
                router.newRootScreen(Screens.Flow.splash())
            }
            processed = true
        }

        return processed
    }

    @Suppress("DEPRECATION")
    private fun initWindowFlags() {
        var flags = window.decorView.systemUiVisibility
        flags = flags or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        flags = flags or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        flags = flags or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        window.decorView.systemUiVisibility = flags
    }

    @Suppress("DEPRECATION")
    private fun updateTaskDescription() {
        val taskDesc = if (Build.VERSION.SDK_INT >= 28) {
            ActivityManager.TaskDescription(
                resources.getString(R.string.app_name),
                R.mipmap.ic_launcher,
                ContextCompat.getColor(this, R.color.colorTextWhite)
            )
        } else {
            ActivityManager.TaskDescription(
                resources.getString(R.string.app_name),
                BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher),
                ContextCompat.getColor(this, R.color.colorTextWhite)
            )
        }

        setTaskDescription(taskDesc)
    }

    private fun onNextMessageNotify(systemMessage: SystemMessage) {
        val text = systemMessage.textRes
            ?.let { getString(it) }
            ?: systemMessage.text
            ?: return
        val actionText = systemMessage.actionTextRes
            ?.let { getString(it) }
            ?: systemMessage.actionText.orEmpty()

        when (systemMessage.type) {
            SystemMessage.Type.BAR -> showBarMessage(text, systemMessage.level)
            SystemMessage.Type.ALERT -> showAlertMessage(text)
            SystemMessage.Type.ACTION -> showActionMessage(
                text,
                actionText,
                systemMessage.actionCallback,
                systemMessage.level
            )
        }
    }

    private fun showBarMessage(text: String, level: SystemMessage.Level) {
        if (text.isBlank()) {
            return
        }

        val backgroundResource = R.drawable.bg_snackbar
        val backgroundTint = if (level == SystemMessage.Level.ERROR)
            ContextCompat.getColorStateList(this, R.color.colorRed)
        else ContextCompat.getColorStateList(this, R.color.colorPrimary)

        val snackbar = Snackbar.make(findViewById(R.id.snackBarContainer), text, Snackbar.LENGTH_LONG)
        val snackView = snackbar.view

        snackView.findViewById<TextView>(R.id.snackbar_text).apply {
            isSingleLine = false
            setTextColor(ContextCompat.getColor(this@AppActivity, R.color.colorTextWhite))
        }

        snackView.run {
            addSystemWindowInsetToMargin(
                top = true,
                bottom = true,
                topOffset = resources.getDimension(R.dimen.baseline_grid_small).toInt(),
                bottomOffset = resources.getDimension(R.dimen.baseline_grid_small).toInt()
            )
            requestApplyInsets()
            setBackgroundResource(backgroundResource)
            backgroundTintList = backgroundTint
        }

        snackbar.show()
    }

    private fun showAlertMessage(text: String) {
        if (text.isBlank()) {
            return
        }

        showAlertDialog(
            type = AlertDialogType.ALERT_VERTICAL_1_OPTION_NO_ACCENT,
            message = text,
            cancellable = true,
            actions = listOf(
                AlertDialogAction(getString(R.string.btn_ok)) {
                    it.dismiss()
                }
            )
        )
    }

    private fun showActionMessage(
        text: String,
        action: String?,
        actionCallback: (() -> Unit?)?,
        level: SystemMessage.Level
    ) {
        if (text.isBlank()) {
            return
        }

        val backgroundResource = R.drawable.bg_snackbar
        val backgroundTint = if (level == SystemMessage.Level.ERROR)
            ContextCompat.getColorStateList(this, R.color.colorRed)
        else ContextCompat.getColorStateList(this, R.color.colorPrimary)

        val snackbar =
            Snackbar.make(findViewById(R.id.snackBarContainer), text, Snackbar.LENGTH_LONG)
        val snackView = snackbar.view

        snackView.findViewById<TextView>(R.id.snackbar_text).apply {
            isSingleLine = false
            setTextColor(ContextCompat.getColor(this@AppActivity, R.color.colorTextWhite))
        }

        snackView.run {
            addSystemWindowInsetToMargin(
                top = true,
                bottom = true,
                topOffset = resources.getDimension(R.dimen.baseline_grid_small).toInt(),
                bottomOffset = resources.getDimension(R.dimen.baseline_grid_small).toInt()
            )
            requestApplyInsets()
            setBackgroundResource(backgroundResource)
            backgroundTintList = backgroundTint
        }

        if (!action.isNullOrBlank() && actionCallback != null) {
            snackbar.setAction(action) { actionCallback() }
            snackView.findViewById<Button>(R.id.snackbar_action).apply {
                setTextColor(ContextCompat.getColor(this@AppActivity, R.color.colorTextWhite))
                backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(this@AppActivity, R.color.colorAccent))
            }
        }

        snackbar.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val fragment = supportFragmentManager.findFragmentById(R.id.container)

        if (fragment is FlowFragment) {
            fragment.currentFragment?.onActivityResult(requestCode, resultCode, data)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}