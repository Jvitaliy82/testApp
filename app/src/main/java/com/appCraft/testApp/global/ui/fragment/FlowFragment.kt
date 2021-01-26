package com.appCraft.testApp.global.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.appCraft.testApp.R
import com.github.terrakok.cicerone.androidx.AppScreen
import org.koin.androidx.viewmodel.ViewModelOwner
import pro.appcraft.lib.navigation.AppNavigator
import pro.appcraft.lib.navigation.setLaunchScreen

abstract class FlowFragment(contentView: Int = R.layout.layout_container) : BaseFragment(contentView) {
    val viewModelOwner
        get() = ViewModelOwner.from(this)

    val currentFragment
        get() = childFragmentManager.findFragmentById(R.id.container) as? BaseFragment

    open val launchScreen: AppScreen? = null

    private val navigator: AppNavigator by lazy {
        object : AppNavigator(requireActivity(), childFragmentManager, R.id.container) {
            override fun setupFragmentTransaction(
                fragmentTransaction: FragmentTransaction,
                currentFragment: Fragment?,
                nextFragment: Fragment?
            ) {
                //fix incorrect order lifecycle callback of MainFlowFragment
                fragmentTransaction.setReorderingAllowed(true)

                currentFragment?.exitTransition = null // Fade(Fade.OUT)
                nextFragment?.enterTransition = null // Slide(Gravity.END)
            }

            override fun activityBack() {
                router.finishFlow()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (childFragmentManager.fragments.isEmpty()) {
            launchScreen?.let(navigator::setLaunchScreen)
        }
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed() ?: router.exit()
    }

    override fun onResume() {
        super.onResume()
        navigation.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigation.navigatorHolder.removeNavigator()
        super.onPause()
    }
}
