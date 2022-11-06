package com.sample.marvelapp.domain.repository

import com.sample.marvelapp.data.dto.ComicResponse

interface ComicRepository {
    suspend fun getComicsByCharacterId(id: Int, offset: Int): ComicResponse
}