package com.appcraft.data.storage.repository

import android.content.Context
import com.appcraft.data.global.BaseRepository
import com.appcraft.data.storage.TvShowItemMPEntity
import io.objectbox.BoxStore

class TvShowRepositoryImpl(
    boxStore: BoxStore,
    private val context: Context
) : BaseRepository<TvShowItemMPEntity>(boxStore, TvShowItemMPEntity::class.java), TvShowRepository{

    override fun addTvShow(tvShowItemMPEntity: TvShowItemMPEntity) {
        box.put(tvShowItemMPEntity)
    }

    override fun getAllTvShow(): List<TvShowItemMPEntity> {
       return box.all
    }
}