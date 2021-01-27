package com.appCraft.testApp.ui.fragment.fromWeb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.appCraft.domain.model.TvShowModel
import com.appCraft.testApp.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_recycler.view.*

class TvShowXAdapter : ListAdapter<TvShowModel.TvShowX, TvShowXAdapter.TvShowXViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowXViewHolder {
        return TvShowXViewHolder(
            LayoutInflater.from(parent.context).inflate(
            R.layout.item_recycler,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: TvShowXViewHolder, position: Int) {
        val currentTv = getItem(position)
        holder.bind(currentTv)
    }

    inner class TvShowXViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(tvShowX : TvShowModel.TvShowX) {
            view.apply {
                textName.text = tvShowX.name
                textNetwork.text = tvShowX.network
                textStarted.text = tvShowX.start_date
                textStatus.text = tvShowX.status
                Glide
                    .with(this)
                    .load(tvShowX.image_thumbnail_path)
                    .into(imageTVShow)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<TvShowModel.TvShowX>() {
        override fun areItemsTheSame(oldItem: TvShowModel.TvShowX, newItem: TvShowModel.TvShowX) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: TvShowModel.TvShowX, newItem: TvShowModel.TvShowX) =
            oldItem == newItem

    }
}