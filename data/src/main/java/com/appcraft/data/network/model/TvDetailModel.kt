package com.appcraft.data.network.model

import android.os.Parcelable
import com.appcraft.domain.model.TvDetailItem
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvDetailModel(
    @SerializedName("tvShow") val tvShow: TvDetailItemModel
) : Parcelable

@Parcelize
data class TvDetailItemModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("image_path") val imagePath: String,
    @SerializedName("description") val description: String,
    @SerializedName("country") val country: String,
    @SerializedName("status") val status: String,
    @SerializedName("start_date") val startDate: String,
    @SerializedName("network") val network: String,
) : Parcelable

fun TvDetailModel.toTvDetailItem() = TvDetailItem(
    id = this.tvShow.id,
    name = this.tvShow.name,
    imagePath = this.tvShow.imagePath,
    description = this.tvShow.description,
    country = this.tvShow.country,
    status = this.tvShow.status,
    startDate = this.tvShow.startDate,
    network = this.tvShow.network
)