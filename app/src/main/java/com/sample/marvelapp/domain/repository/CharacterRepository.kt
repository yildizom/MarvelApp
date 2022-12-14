package com.sample.marvelapp.domain.repository

import com.sample.marvelapp.data.dto.CharacterResponse


interface CharacterRepository {
    suspend fun getCharacters(offset: Int): CharacterResponse

    suspend fun getCharacterById(id: Int): CharacterResponse
}