package com.appcraft.testapp.app.ui.fragment.fromWeb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.appcraft.domain.model.TvShowModel
import com.appcraft.testapp.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_recycler.view.*

class TvShowXAdapter(private val listener: OnItemClickListener) : ListAdapter<TvShowModel.TvShowX, TvShowXAdapter.TvShowXViewHolder>(
    DiffCallback()
) {

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

        init {
            view.setOnClickListener {
                val position = absoluteAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val tv = getItem(position)
                    listener.onItemClick(tv)
                }
            }
        }

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

    interface OnItemClickListener {
        fun onItemClick(tvShow: TvShowModel.TvShowX)
        fun addFavorite(tvShow: TvShowModel.TvShowX)
    }
}