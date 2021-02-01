package com.appcraft.data.storage

import com.appcraft.domain.model.TvShowItemMP
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class TvShowItemMPEntity(
    @Id var id: Long = 0,
    var uuid: Long,
    var name: String,
    var start_date: String,
    var country: String,
    var network: String,
    var status: String,
    var end_date: String,
    var image_thumbnail_path: String,
    var permalink: String
)

fun TvShowItemMP.toTvShowMPEntity(): TvShowItemMPEntity = TvShowItemMPEntity(
    uuid = this.uuid,
    name = this.name,
    start_date = this.start_date,
    country = this.country,
    network = this.network,
    status = this.status,
    end_date = this.end_date,
    image_thumbnail_path = this.image_thumbnail_path,
    permalink = this.permalink
)