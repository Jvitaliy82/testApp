package com.appcraft.testapp.app.global.ui.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.androidx.AppScreen
import moxy.MvpAppCompatFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import pro.appcraft.lib.navigation.FlowNavigationViewModel
import pro.appcraft.lib.utils.common.hideKeyboard

abstract class BaseFragment(@LayoutRes private val contentView: Int) : MvpAppCompatFragment() {
    open var isLightStatusBar: Boolean = true
        protected set
    open var isLightNavigationBar: Boolean = true
        protected set

    private val flowParent
        get() = this as? FlowFragment ?: getParent(this)

    val navigation: FlowNavigationViewModel by sharedViewModel(owner = { flowParent.viewModelOwner })

    protected val router by lazy { navigation.router }

    private fun getParent(fragment: Fragment): FlowFragment {
        return when {
            fragment is FlowFragment -> fragment
            fragment.parentFragment == null ->
                throw IllegalStateException("Fragment must have FlowFragment or Activity parent")
            else -> getParent(fragment.requireParentFragment())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(contentView, container, false)
    }

    override fun onDestroyView() {
        hideKeyboard()
        super.onDestroyView()
    }

    open fun onBackPressed() {
        router.exit()
    }

    override fun onResume() {
        super.onResume()
        initStatusAndNavigationBar()
    }

    private fun initStatusAndNavigationBar() {
        if (this is FlowFragment || childFragmentManager.fragments.isNotEmpty()) {
            return
        }
        updateSystemBarsColors()
    }

    fun updateSystemBarsColors() {
        applyStatusBarMode()
        applyNavigationBarMode()
    }

    fun setStatusBarMode(isLightStatusBar: Boolean) {
        this.isLightStatusBar = isLightStatusBar
        applyStatusBarMode()
    }

    private fun applyStatusBarMode() {
        var flags = requireActivity().window.decorView.systemUiVisibility

        flags = if (isLightStatusBar) {
            flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        }

        requireActivity().window.decorView.systemUiVisibility = flags
    }

    private fun applyNavigationBarMode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            activity?.apply {
                var flags = window.decorView.systemUiVisibility
                flags = if (isLightNavigationBar) {
                    flags or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                } else {
                    flags and View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR.inv()
                }
                window.decorView.systemUiVisibility = flags
            }
        }
    }

    fun routerNavigateTo(screen: AppScreen) = router.navigateTo(screen)

    fun routerNewRootScreen(screen: AppScreen) = router.newRootScreen(screen)

    fun routerNewRootChain(vararg screens: AppScreen) = router.newRootChain(*screens)

    fun routerExit() = router.exit()

    fun routerStartFlow(flow: AppScreen) = router.startFlow(flow)

    fun routerReplaceScreen(flow: AppScreen) = router.replaceScreen(flow)

    fun routerFinishFlow() = router.finishFlow()

    fun routerBackTo(flow: AppScreen) = router.backTo(flow)

    fun routerForwardTo(flow: AppScreen) = router.forwardTo(flow)

    fun routerToTop(flow: AppScreen) = router.toTop(flow)
}
