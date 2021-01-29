package com.appcraft.data.storage.repository

import com.appcraft.domain.model.TvShowModel

interface TvShowRepository {

    fun addTvShow(tvShowX: TvShowModel.TvShowX)
}