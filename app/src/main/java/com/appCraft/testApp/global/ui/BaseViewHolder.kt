package com.appCraft.testApp.global.ui

import android.content.Context
import android.content.res.Resources
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.IItem
import kotlinx.android.extensions.LayoutContainer

abstract class BaseViewHolder<T : IItem<*>>(override val containerView: View) :
    FastAdapter.ViewHolder<T>(containerView), LayoutContainer {

    val context: Context = itemView.context

    val resources: Resources = itemView.resources

    fun getColor(@ColorRes color: Int) = ContextCompat.getColor(itemView.context, color)

    fun getDrawable(@DrawableRes drawable: Int) =
        ContextCompat.getDrawable(itemView.context, drawable)

    fun getString(@StringRes stringRes: Int): String = itemView.context.getString(stringRes)

    fun getString(@StringRes stringRes: Int, vararg formatArgs: Any): String =
        itemView.context.getString(stringRes, *formatArgs)


    override fun bindView(item: T, payloads: List<Any>) {}

    override fun unbindView(item: T) {}
}