package com.appcraft.domain.model

data class TvDetailItem(
    val id: Int,
    val name: String,
    val imagePath: String,
    val description: String,
    val country: String,
    val status: String,
    val startDate: String,
    val network: String
)