package com.appcraft.testapp.app.ui.fragment.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commitNow
import com.appcraft.testapp.R
import com.appcraft.testapp.app.global.ui.fragment.BaseFragment
import com.appcraft.testapp.app.presentation.main.MainPresenter
import com.appcraft.testapp.app.presentation.main.MainView
import com.appcraft.testapp.app.ui.fragment.fromWeb.FromWebFragment
import com.appcraft.testapp.app.ui.fragment.saved.SavedFragment
import com.appcraft.testapp.app.utils.MainTab
import kotlinx.android.synthetic.main.fragment_main.*
import moxy.presenter.InjectPresenter
import pro.appcraft.lib.utils.common.addSystemWindowInsetToMargin
import java.util.*

class MainFragment : BaseFragment(R.layout.fragment_main), MainView {

    override var isLightStatusBar = false

    @InjectPresenter
    lateinit var presenter: MainPresenter

    private val tabFragments: MutableMap<MainTab, Fragment> = EnumMap(MainTab::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContainers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        container.addSystemWindowInsetToMargin(top = true)
        initNavigation()
        bottom_nav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.from_web -> {
                    openFragmentByTab(MainTab.WEB)
                    true
                }
                R.id.saved -> {
                    openFragmentByTab(MainTab.SAVED)
                    true
                }
                else -> false
            }
        }
    }

    private fun initContainers() {
        tabFragments.clear()
        tabFragments.putAll(
            mapOf(
                MainTab.WEB to getFragmentByTag(
                    childFragmentManager,
                    MainTab.WEB.toString(),
                    FromWebFragment.newInstance()
                ),
                MainTab.SAVED to getFragmentByTag(
                    childFragmentManager,
                    MainTab.SAVED.toString(),
                    SavedFragment.newInstance()
                )
            )
        )
    }

    private fun initNavigation() {
        openFragmentByTab(presenter.selectedNavigationTab)
    }

    private fun openFragmentByTab(tab: MainTab) {
        childFragmentManager.commitNow {
//                this.setCustomAnimations(
//                    R.anim.fade_in, R.anim.fade_out,
//                    R.anim.fade_in, R.anim.fade_out
//                )
            for (tabFragment in tabFragments) {
                if (tabFragment.key == tab) {
                    attach(tabFragment.value)
                } else {
                    detach(tabFragment.value)
                }
            }
        }

        presenter.selectedNavigationTab = tab
    }

    private fun getFragmentByTag(
        fm: FragmentManager,
        tag: String,
        defaultFragment: Fragment
    ): Fragment {
        var fragment = fm.findFragmentByTag(tag)
        if (fragment == null) {
            fragment = defaultFragment
            fm.commitNow {
                add(R.id.fragmentContainer, fragment, tag)
                detach(fragment)
            }
        }

        return fragment
    }

    private fun setupNavigationItemId(tab: MainTab) {
        presenter.selectedNavigationTab = tab
    }
}