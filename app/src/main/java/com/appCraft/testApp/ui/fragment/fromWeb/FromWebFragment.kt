package com.appCraft.testApp.ui.fragment.fromWeb

import com.appCraft.testApp.R
import com.appCraft.testApp.global.ui.fragment.BaseFragment
import com.appCraft.testApp.presentation.fromWeb.FromWebView

class FromWebFragment: BaseFragment(R.layout.fragment_from_web), FromWebView {

    companion object {
        fun newInstance(): FromWebFragment {
            return FromWebFragment()
        }
    }
}