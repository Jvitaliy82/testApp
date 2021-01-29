package com.appCraft.testApp.ui.fragment.detail

import com.appCraft.testApp.Screens
import com.appCraft.testApp.global.ui.fragment.FlowFragment

class DetailFlowFragment : FlowFragment() {

    override val launchScreen
        get() = Screens.Screen.detail(
            arguments?.getInt(ID)
        )
    companion object {
        const val ID = "ID"
    }
}