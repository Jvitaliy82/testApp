package com.appCraft.testApp.ui.fragment.splash

import com.appCraft.testApp.Screens
import com.appCraft.testApp.global.ui.fragment.FlowFragment
import com.github.terrakok.cicerone.androidx.AppScreen

class SplashFlowFragment : FlowFragment() {
    override val launchScreen: AppScreen? = Screens.Screen.splash()
}
