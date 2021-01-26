package com.appCraft.testApp.ui.fragment.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.appCraft.testApp.R
import com.appCraft.testApp.global.ui.fragment.BaseFragment
import com.appCraft.testApp.presentation.splash.SplashPresenter
import com.appCraft.testApp.presentation.splash.SplashView
import moxy.presenter.InjectPresenter

private const val ANIMATION_DURATION_MS = 3000L

class SplashFragment : BaseFragment(R.layout.fragment_splash), SplashView {
    override var isLightStatusBar = true

    @InjectPresenter
    lateinit var presenter: SplashPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startFirstAnimation()
        // activate this if splash background color differs from main window background color
//        view.postDelayed(
//            { activity?.window?.setBackgroundDrawableResource(R.color.colorBackground) },
//            ANIMATION_DURATION_MS / 2
//        )
    }

    private fun startFirstAnimation() {
        Handler(Looper.getMainLooper()).postDelayed(
            {
                presenter.onAnimationEnd()
            },
            ANIMATION_DURATION_MS
        )
    }
}