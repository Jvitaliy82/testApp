package com.appCraft.data.storage.repository

import com.appCraft.domain.model.TvShowModel

interface TvShowRepository {

    fun addTvShow(tvShowX: TvShowModel.TvShowX)
}