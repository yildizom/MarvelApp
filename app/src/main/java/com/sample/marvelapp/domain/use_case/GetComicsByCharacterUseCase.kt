package com.sample.marvelapp.domain.use_case

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sample.marvelapp.domain.model.Comic
import com.sample.marvelapp.domain.repository.ComicRepository
import com.sample.marvelapp.domain.use_case.pagingsource.ComicsPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetComicsByCharacterUseCase @Inject constructor(private val comicRepository: ComicRepository) {
    operator fun invoke(characterId: Int, pageSize: Int = 30): Flow<PagingData<Comic>> = Pager(
        PagingConfig(
            pageSize = pageSize,
        )
    ) {
        ComicsPagingSource(characterId, comicRepository)
    }.flow
}