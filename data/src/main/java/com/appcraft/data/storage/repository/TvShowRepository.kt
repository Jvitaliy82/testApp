package com.appcraft.data.storage.repository

import com.appcraft.data.storage.TvShowItemMPEntity


interface TvShowRepository {

    fun addTvShow(tvShowItemMPEntity: TvShowItemMPEntity)

    fun getAllTvShow() : List<TvShowItemMPEntity>

    fun getTvShowByName(name: String) : List<TvShowItemMPEntity>

    fun deleteItem(tvShowItemMPEntity: TvShowItemMPEntity)
}