package com.sample.marvelapp.di

import com.sample.marvelapp.data.MarvelApi
import com.sample.marvelapp.data.repository.CharacterRepositoryImpl
import com.sample.marvelapp.domain.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCharacterRepository(marvelApi: MarvelApi): CharacterRepository {
        return CharacterRepositoryImpl(marvelApi)
    }
}