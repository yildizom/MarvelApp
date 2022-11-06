package com.sample.marvelapp.domain.model

import com.sample.marvelapp.data.dto.ComicDto

data class Comic(val id: Int, val title: String, val description: String, val thumbnail: String)

fun ComicDto.toComic(): Comic {
    return Comic(id, title, description, thumbnail.path + "." + thumbnail.extension)
}