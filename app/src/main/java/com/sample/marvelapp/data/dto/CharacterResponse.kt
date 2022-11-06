package com.sample.marvelapp.data.dto

data class CharacterResponse(val data: CharacterResponseData)

data class CharacterResponseData(
    val offset: Int,
    val limit: Int,
    val page: Int,
    val total: Int,
    val count: Int,
    val results: List<CharacterDto>
)