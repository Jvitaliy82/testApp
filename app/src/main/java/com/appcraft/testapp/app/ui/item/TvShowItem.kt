package com.appcraft.testapp.app.ui.item

import android.view.View
import com.appcraft.domain.model.TvShowItemMP
import com.appcraft.testapp.R
import com.appcraft.testapp.app.global.ui.BaseViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.mikepenz.fastadapter.items.AbstractItem
import kotlinx.android.synthetic.main.item_recycler.*
import kotlinx.android.synthetic.main.item_recycler.view.*

class TvShowItem(
    val tvItem: TvShowItemMP
) : AbstractItem<TvShowItem.ViewHolder>() {

    override val layoutRes: Int
        get() = R.layout.item_recycler

    override val type: Int
        get() = R.id.TvItem

    override fun getViewHolder(v: View): ViewHolder = ViewHolder(v)

    class ViewHolder(view: View) : BaseViewHolder<TvShowItem>(view) {
        override fun bindView(item: TvShowItem, payloads: List<Any>) {
            itemView.textName.text = item.tvItem.name
            itemView.textNetwork.text = item.tvItem.network
            itemView.textStarted.text = item.tvItem.startDate
            itemView.textStatus.text = item.tvItem.status
            Glide
                .with(context)
                .load(item.tvItem.imageThumbnailPath)
                .transform(CenterCrop(), RoundedCorners(16))
                .into(imageTVShow)
        }
    }
}