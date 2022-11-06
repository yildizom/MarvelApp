package com.sample.marvelapp.data.repository

import com.sample.marvelapp.data.MarvelApi
import com.sample.marvelapp.data.dto.CharacterResponse
import com.sample.marvelapp.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val marvelApi: MarvelApi
): CharacterRepository {

    override suspend fun getCharacters(page: Int): CharacterResponse {
        return marvelApi.getCharacters(page)
    }

    override suspend fun getCharacterById(id: Int): CharacterResponse {
        return marvelApi.getCharacterById(id)
    }
}