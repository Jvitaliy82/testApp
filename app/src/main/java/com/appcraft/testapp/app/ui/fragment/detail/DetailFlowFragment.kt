package com.appcraft.testapp.app.ui.fragment.detail

import com.appcraft.testapp.app.Screens
import com.appcraft.testapp.app.global.ui.fragment.FlowFragment

class DetailFlowFragment : FlowFragment() {

    override val launchScreen
        get() = Screens.Screen.detail(
            arguments?.getParcelable(ID)!!
        )
    companion object {
        const val ID = "ID"
    }
}