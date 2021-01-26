package com.appCraft.testApp.ui.fragment.saved

import com.appCraft.testApp.R
import com.appCraft.testApp.global.ui.fragment.BaseFragment
import com.appCraft.testApp.presentation.fromWeb.FromWebView

class SavedFragment: BaseFragment(R.layout.fragment_saved), FromWebView {

    companion object {
        fun newInstance(): SavedFragment {
            return SavedFragment()
        }
    }
}