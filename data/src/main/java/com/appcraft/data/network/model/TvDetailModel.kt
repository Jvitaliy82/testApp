package com.appcraft.data.network.model

import android.os.Parcelable
import com.appcraft.domain.model.TvDetail
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

fun TvDetailModel.toTvDetail() = TvDetail(
    tvShow = this.tvShow.toTvDetailItem()
)

fun TvDetailItemModel.toTvDetailItem() = TvDetailItem(
    id = this.id,
    name = this.name,
    imagePath = this.imagePath,
    description = this.description,
    country = this.country,
    status = this.status,
    startDate = this.startDate,
    network = this.network
)