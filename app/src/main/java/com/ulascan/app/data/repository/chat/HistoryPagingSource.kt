package com.ulascan.app.data.repository.chat

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ulascan.app.data.remote.api.ApiService
import com.ulascan.app.data.remote.response.HistoriesItem

class HistoryPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, HistoriesItem>() {
    override fun getRefreshKey(state: PagingState<Int, HistoriesItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HistoriesItem> {
        TODO("Not yet implemented")
    }

}
