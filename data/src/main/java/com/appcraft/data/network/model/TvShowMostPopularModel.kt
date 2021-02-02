package com.appcraft.data.network.model

import android.os.Parcelable
import com.appcraft.domain.model.TvShowMostPopular
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowMostPopularModel(
    @SerializedName("page") val page: Int,
    @SerializedName("pages") val pages: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("tv_shows") val tvShowmodels: List<TvShowItemMPModel>
) : Parcelable

fun TvShowMostPopularModel.toTvShowMostPopular() = TvShowMostPopular(
    page = this.page,
    pages = this.pages,
    total = this.total,
    tvShowModels = this.tvShowmodels.map { it.toTvShowItemMP() }
)







