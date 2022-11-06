package com.sample.marvelapp.domain.use_case.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sample.marvelapp.domain.model.Comic
import com.sample.marvelapp.domain.model.toComic
import com.sample.marvelapp.domain.repository.ComicRepository
import timber.log.Timber

class ComicsPagingSource(private val characterId: Int, private val comicRepository: ComicRepository):
    PagingSource<Int, Comic>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Comic> {
        return try {
            val nextOffset = params.key ?: 0
            val data = comicRepository.getComicsByCharacterId(characterId, nextOffset).data
            LoadResult.Page(
                data = data.results.map { it.toComic() },
                prevKey = null,
                nextKey = getNextKey(data.offset, data.limit, data.count)
            )
        }
        catch (e: Exception) {
            Timber.e("Pagination error: ${e.localizedMessage}")
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Comic>): Int? {
        return null
    }

    private fun getNextKey(offset: Int, limit: Int, count: Int): Int? {
        if (count < limit) {
            return null
        }
        return offset + count
    }
}