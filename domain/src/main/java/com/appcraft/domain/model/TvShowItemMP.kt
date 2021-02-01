package com.appcraft.domain.model

data class TvShowItemMP(
    val id: Long = 0,
    val uuid: Long,
    val name: String,
    val start_date: String,
    val country: String,
    val network: String,
    val status: String,
    val end_date: String,
    val image_thumbnail_path: String,
    val permalink: String
)