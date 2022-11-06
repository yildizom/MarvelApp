package com.sample.marvelapp.data

import com.sample.marvelapp.data.dto.CharacterResponse
import com.sample.marvelapp.data.dto.ComicResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 30
    ): CharacterResponse

    @GET("/v1/public/characters/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): CharacterResponse

    @GET("/v1/public/characters/{id}/comics")
    suspend fun getComicsByCharacterId(
        @Path("id") id: Int,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 30
    ): ComicResponse
}