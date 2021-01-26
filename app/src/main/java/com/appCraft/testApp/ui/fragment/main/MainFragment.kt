package com.appCraft.testApp.ui.fragment.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commitNow
import com.appCraft.testApp.R
import com.appCraft.testApp.global.ui.fragment.BaseFragment
import com.appCraft.testApp.presentation.main.MainPresenter
import com.appCraft.testApp.presentation.main.MainView
import com.appCraft.testApp.ui.fragment.fromWeb.FromWebFragment
import com.appCraft.testApp.ui.fragment.saved.SavedFragment
import com.appCraft.testApp.utils.MainTab
import kotlinx.android.synthetic.main.fragment_main.*
import moxy.presenter.InjectPresenter
import java.util.*

class MainFragment : BaseFragment(R.layout.fragment_main), MainView {

    @InjectPresenter
    lateinit var presenter: MainPresenter

    private val tabFragments: MutableMap<MainTab, Fragment> = EnumMap(MainTab::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContainers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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