package com.appcraft.testapp.app.ui.fragment.fromWeb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.appcraft.domain.model.TvShowItemMP
import com.appcraft.testapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kotlinx.android.synthetic.main.item_recycler.view.*


class TvShowXAdapter(private val listener: OnItemClickListener) : ListAdapter<TvShowItemMP, TvShowXAdapter.TvShowXViewHolder>(
    DiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowXViewHolder {
        return TvShowXViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_recycler,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TvShowXViewHolder, position: Int) {
        val currentTv = getItem(position)
        holder.bind(currentTv)
    }

    inner class TvShowXViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        init {
            view.setOnClickListener {
                val position = absoluteAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val tv = getItem(position)
                    listener.onItemClick(tv)
                }
            }
        }

        fun bind(tvShowXModel: TvShowItemMP) {
            view.apply {
                textName.text = tvShowXModel.name
                textNetwork.text = tvShowXModel.network
                textStarted.text = tvShowXModel.start_date
                textStatus.text = tvShowXModel.status
                Glide
                    .with(this)
                    .load(tvShowXModel.image_thumbnail_path)
                    .transform(CenterCrop(), RoundedCorners(16))
                    .into(imageTVShow)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<TvShowItemMP>() {
        override fun areItemsTheSame(oldItem: TvShowItemMP, newItem: TvShowItemMP) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: TvShowItemMP, newItem: TvShowItemMP) =
            oldItem == newItem

    }

    interface OnItemClickListener {
        fun onItemClick(tvShowModel: TvShowItemMP)
        fun addFavorite(tvShowModel: TvShowItemMP)
    }
}