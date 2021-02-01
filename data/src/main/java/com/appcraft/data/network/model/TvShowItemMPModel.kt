package com.appcraft.data.network.model

import android.os.Parcelable
import com.appcraft.domain.model.TvShowItemMP
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowItemMPModel(
    @SerializedName("id") val uuid: Long,
    @SerializedName("name") val name: String,
    @SerializedName("start_date") val start_date: String,
    @SerializedName("country") val country: String,
    @SerializedName("network") val network: String,
    @SerializedName("status") val status: String,
    @SerializedName("end_date") val end_date: String?,
    @SerializedName("image_thumbnail_path") val image_thumbnail_path: String,
    @SerializedName("permalink") val permalink: String
): Parcelable

fun TvShowItemMPModel.toTvShowItemMP() = TvShowItemMP(
    uuid = this.uuid,
    name = this.name,
    start_date = this.start_date,
    country = this.country,
    network = this.network,
    status = this.status,
    end_date = this.end_date ?: "",
    image_thumbnail_path = this.image_thumbnail_path,
    permalink = this.permalink
)