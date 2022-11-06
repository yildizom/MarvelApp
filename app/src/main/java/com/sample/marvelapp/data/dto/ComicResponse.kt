package com.sample.marvelapp.data.dto

data class ComicResponse(val data: CharacterResponseData)

data class ComicResponseData(val offset: Int, val limit: Int, val count: Int, val results: List<ComicDto>)