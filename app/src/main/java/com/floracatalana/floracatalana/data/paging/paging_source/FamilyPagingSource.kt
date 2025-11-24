package com.floracatalana.floracatalana.data.paging.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.floracatalana.floracatalana.data.remote.FloracatalanaApi
import com.floracatalana.floracatalana.data.remote.dto.FamilyListResponse

class FamilyPagingSource(
    private val api: FloracatalanaApi
): PagingSource<Int, FamilyListResponse>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FamilyListResponse> {
        return try {
            val page = params.key ?: 0

            val response = api.getFamilyList(page = page)
            LoadResult.Page(
                data = response,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, FamilyListResponse>): Int? {
        return state.anchorPosition?.let { position ->
            val anchorPage = state.closestPageToPosition(position)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}