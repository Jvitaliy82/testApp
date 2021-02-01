package com.appcraft.domain.model

data class TvDetailItem(
    val id: Int,
    val name: String,
    val image_path: String,
    val description: String,
    val country: String,
    val status: String,
    val start_date: String,
    val network: String
)