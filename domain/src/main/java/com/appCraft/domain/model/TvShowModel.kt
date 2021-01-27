package com.appCraft.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowModel(
    val page: Int,
    val pages: Int,
    val total: Int,
    val tv_shows: MutableList<TvShowX>
) : Parcelable {
    @Parcelize
    data class TvShowX(
        val id: String,
        val name: String,
        val start_date: String,
        val country: String,
        val network: String,
        val status: String,
        val end_date: String?,
        val image_thumbnail_path: String,
        val permalink: String
    ) : Parcelable
}





