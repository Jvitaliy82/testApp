package com.appcraft.testapp.app.ui.fragment.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.appcraft.testapp.R
import com.appcraft.testapp.app.global.ui.fragment.BaseFragment
import com.appcraft.testapp.app.presentation.splash.SplashPresenter
import com.appcraft.testapp.app.presentation.splash.SplashView
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