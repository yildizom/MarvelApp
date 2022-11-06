package com.sample.marvelapp.domain.use_case.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sample.marvelapp.domain.repository.CharacterRepository
import com.sample.marvelapp.domain.model.Character
import com.sample.marvelapp.domain.model.toCharacter
import timber.log.Timber
import kotlin.math.max
import kotlin.math.min

class CharacterPagingSource(private val characterRepository: CharacterRepository):
    PagingSource<Int, Character>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val nextOffset = params.key ?: 0
            val data = characterRepository.getCharacters(nextOffset).data
            LoadResult.Page(
                data = data.results.map { it.toCharacter() },
                prevKey = null,
                nextKey = getNextKey(data.offset, data.limit, data.count)
            )
        }
        catch (e: Exception) {
            Timber.e("Pagination error: ${e.localizedMessage}")
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return null
    }

    private fun getNextKey(offset: Int, limit: Int, count: Int): Int? {
        if (offset + limit > count) {
            return null
        }
        return offset + limit
    }
}