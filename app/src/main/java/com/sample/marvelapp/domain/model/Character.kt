package com.sample.marvelapp.domain.model

import com.sample.marvelapp.data.dto.CharacterDto

data class Character(val id: Int, val name: String, val description: String, val thumbnail: String)

fun CharacterDto.toCharacter(): Character {
    return Character(id, name, description, thumbnail.path + "." + thumbnail.extension)
}