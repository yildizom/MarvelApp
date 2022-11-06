package com.sample.marvelapp.domain.use_case

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sample.marvelapp.domain.repository.CharacterRepository
import com.sample.marvelapp.domain.model.Character
import com.sample.marvelapp.domain.use_case.pagingsource.CharacterPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {

    operator fun invoke(pageSize: Int = 30): Flow<PagingData<Character>> = Pager(
        PagingConfig(
            pageSize = pageSize,
        )
    ) {
        CharacterPagingSource(characterRepository)
    }.flow
}