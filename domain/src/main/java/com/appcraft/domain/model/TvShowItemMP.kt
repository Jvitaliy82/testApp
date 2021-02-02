package com.appcraft.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowItemMP(
    val id: Long = 0,
    val uuid: Long,
    val name: String,
    val startDate: String,
    val country: String,
    val network: String,
    val status: String,
    val endDate: String,
    val imageThumbnailPath: String,
    val permalink: String
) : Parcelable