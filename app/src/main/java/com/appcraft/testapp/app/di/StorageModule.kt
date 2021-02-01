package com.appcraft.testapp.app.di

import com.appcraft.data.storage.MyObjectBox
import com.appcraft.data.storage.repository.TvShowRepository
import com.appcraft.data.storage.repository.TvShowRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

@Suppress("USELESS_CAST")
val storageModule = module {
    single { MyObjectBox.builder().androidContext(androidContext()).build() }
    single { TvShowRepositoryImpl(get(), androidContext()) as TvShowRepository }
//    single { AppearanceFeatureTypeRepositoryImpl(get(), get()) as AppearanceFeatureTypeRepository }
//    single { BookRepositoryImpl(get(), get(), get(), get()) as BookRepository }
//    single { CharacterRepositoryImpl(get(), get(), get(), androidContext()) as CharacterRepository }
//    single { GroupRepositoryImpl(get(), get()) as GroupRepository }
//    single { NoteRepositoryImpl(get(), get(), androidContext()) as NoteRepository }
//    single { NoteTagRepositoryImpl(get()) as NoteTagRepository }
//    single { PersonalityFeatureRepositoryImpl(get()) as PersonalityFeatureRepository }
//    single { PersonalityFeatureTypeRepositoryImpl(get(), get()) as PersonalityFeatureTypeRepository }
//    single { SceneRepositoryImpl(get()) as SceneRepository }
//    single { SceneTagRepositoryImpl(get()) as SceneTagRepository }
}
