package com.appcraft.data.storage.repository

import com.appcraft.data.storage.TvShowItemMPEntity


interface TvShowRepository {

    fun addTvShow(tvShowItemMPEntity: TvShowItemMPEntity)

    fun getAllTvShow() : List<TvShowItemMPEntity>

    fun getTvShowById(id: Long) : TvShowItemMPEntity?

    fun deleteItem(tvShowItemMPEntity: TvShowItemMPEntity)
}