package com.sample.marvelapp.data.repository

import com.sample.marvelapp.data.MarvelApi
import com.sample.marvelapp.data.dto.ComicResponse
import com.sample.marvelapp.domain.repository.ComicRepository

class ComicRepositoryImpl(private val marvelApi: MarvelApi): ComicRepository {
    override suspend fun getComicsByCharacterId(id: Int, offset: Int): ComicResponse {
        return marvelApi.getComicsByCharacterId(id = id, offset = offset, limit = 30)
    }
}