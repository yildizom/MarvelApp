package com.sample.marvelapp.domain.use_case

import com.sample.marvelapp.domain.repository.CharacterRepository
import com.sample.marvelapp.domain.model.Character
import com.sample.marvelapp.domain.model.Res
import com.sample.marvelapp.domain.model.toCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {

    operator fun invoke(id: Int): Flow<Res<Character>> = flow {
        emit(Res.Loading)
        try {
            val character = characterRepository.getCharacterById(id).data.results.first().toCharacter()
            emit(Res.Success(character))
        } catch (e: Exception) {
            emit(Res.Error(e.localizedMessage))
        }
    }
}