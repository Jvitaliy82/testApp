package com.appcraft.testapp.ui.fragment.splash

import com.appcraft.testapp.Screens
import com.appcraft.testapp.global.ui.fragment.FlowFragment
import com.github.terrakok.cicerone.androidx.AppScreen

class SplashFlowFragment : FlowFragment() {
    override val launchScreen: AppScreen? = Screens.Screen.splash()
}
