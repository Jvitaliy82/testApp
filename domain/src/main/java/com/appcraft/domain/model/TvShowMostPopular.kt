package com.appcraft.domain.model

data class TvShowMostPopular(
    val page: Int,
    val pages: Int,
    val total: Int,
    val tvShowModels: List<TvShowItemMP>
)