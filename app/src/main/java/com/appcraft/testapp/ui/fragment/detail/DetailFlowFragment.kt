package com.appcraft.testapp.ui.fragment.detail

import com.appcraft.testapp.Screens
import com.appcraft.testapp.global.ui.fragment.FlowFragment

class DetailFlowFragment : FlowFragment() {

    override val launchScreen
        get() = Screens.Screen.detail(
            arguments?.getInt(ID)
        )
    companion object {
        const val ID = "ID"
    }
}