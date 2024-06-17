package com.ulascan.app.data.repository.chat

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ulascan.app.data.remote.api.ApiService
import com.ulascan.app.data.remote.response.HistoriesItem
import retrofit2.HttpException
import java.io.IOException

class HistoryPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, HistoriesItem>() {
    override fun getRefreshKey(state: PagingState<Int, HistoriesItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HistoriesItem> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getHistories(
                page = currentPage,
                limit = params.loadSize
            )

            LoadResult.Page(
                data = response.data.histories,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (response.data.histories.isEmpty()) null else response.data.page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

}
