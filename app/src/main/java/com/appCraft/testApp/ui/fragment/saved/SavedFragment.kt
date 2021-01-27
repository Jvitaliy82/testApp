package com.appCraft.testApp.ui.fragment.saved

import com.appCraft.testApp.R
import com.appCraft.testApp.global.ui.fragment.BaseFragment
import com.appCraft.testApp.presentation.saved.SavedPresenter
import com.appCraft.testApp.presentation.saved.SavedView
import moxy.presenter.InjectPresenter

class SavedFragment: BaseFragment(R.layout.fragment_saved), SavedView {

    @InjectPresenter
    lateinit var presenter: SavedPresenter

    companion object {
        fun newInstance(): SavedFragment {
            return SavedFragment()
        }
    }
}