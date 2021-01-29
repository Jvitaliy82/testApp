package com.appcraft.testapp.ui.fragment.saved

import com.appcraft.testapp.R
import com.appcraft.testapp.global.ui.fragment.BaseFragment
import com.appcraft.testapp.presentation.saved.SavedPresenter
import com.appcraft.testapp.presentation.saved.SavedView
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