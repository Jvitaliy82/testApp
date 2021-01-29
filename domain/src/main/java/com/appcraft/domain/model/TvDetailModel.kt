package com.appcraft.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvDetailModel(
    val tvShow: TvShow
) : Parcelable {
    @Parcelize
    data class TvShow(
        val id: Int,
        val name: String,
        val image_path: String,
        val description: String,
        val country: String,
        val status: String,
        val start_date: String,
        val network: String,
    ) : Parcelable
}