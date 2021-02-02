package com.appcraft.data.network.model

import android.os.Parcelable
import com.appcraft.domain.model.TvShowItemMP
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowItemMPModel(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("start_date") val startDate: String,
    @SerializedName("country") val country: String,
    @SerializedName("network") val network: String,
    @SerializedName("status") val status: String,
    @SerializedName("end_date") val endDate: String?,
    @SerializedName("image_thumbnail_path") val imageThumbnailPath: String,
    @SerializedName("permalink") val permalink: String
): Parcelable

fun TvShowItemMPModel.toTvShowItemMP() = TvShowItemMP(
    id = this.id,
    name = this.name,
    startDate = this.startDate,
    country = this.country,
    network = this.network,
    status = this.status,
    endDate = this.endDate ?: "",
    imageThumbnailPath = this.imageThumbnailPath,
    permalink = this.permalink
)