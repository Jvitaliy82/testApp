package com.appcraft.testapp.app.ui.fragment.splash

import com.appcraft.testapp.app.Screens
import com.appcraft.testapp.app.global.ui.fragment.FlowFragment
import com.github.terrakok.cicerone.androidx.AppScreen

class SplashFlowFragment : FlowFragment() {
    override val launchScreen: AppScreen? = Screens.Screen.splash()
}
