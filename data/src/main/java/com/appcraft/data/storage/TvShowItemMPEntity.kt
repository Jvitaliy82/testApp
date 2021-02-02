package com.appcraft.data.storage

import com.appcraft.domain.model.TvShowItemMP
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class TvShowItemMPEntity(
    @Id(assignable = true) var id: Long = 0,
    var name: String,
    var startDate: String,
    var country: String,
    var network: String,
    var status: String,
    var endDate: String,
    var imageThumbnailPath: String,
    var permalink: String
)

fun TvShowItemMP.toTvShowMPEntity(): TvShowItemMPEntity = TvShowItemMPEntity(
    id = this.id,
    name = this.name,
    startDate = this.startDate,
    country = this.country,
    network = this.network,
    status = this.status,
    endDate = this.endDate,
    imageThumbnailPath = this.imageThumbnailPath,
    permalink = this.permalink
)

fun TvShowItemMPEntity.toTvShowItemMP(): TvShowItemMP = TvShowItemMP(
    id = this.id,
    name = this.name,
    startDate = this.startDate,
    country = this.country,
    network = this.network,
    status = this.status,
    endDate = this.endDate,
    imageThumbnailPath = this.imageThumbnailPath,
    permalink = this.permalink
)