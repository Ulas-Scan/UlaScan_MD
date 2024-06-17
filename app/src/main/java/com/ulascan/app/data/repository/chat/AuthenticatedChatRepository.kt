package com.ulascan.app.data.repository.chat

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ulascan.app.data.remote.api.ApiService
import com.ulascan.app.data.remote.response.AnalysisResponse
import com.ulascan.app.data.remote.response.HistoriesItem
import com.ulascan.app.data.remote.response.HistoriesResponse
import com.ulascan.app.data.repository.UserChatRepository
import com.ulascan.app.data.states.ResultState
import com.ulascan.app.utils.getErrorMessage
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException

class AuthenticatedChatRepository(private val apiService: ApiService) : UserChatRepository {
  override suspend fun getHistory(): ResultState<HistoriesResponse> {
    return try {
      val response = apiService.getHistories()
      ResultState.Success(response)
    } catch (error: HttpException) {
      ResultState.Error(error.getErrorMessage())
    }
  }

  override suspend fun getHistoryWithPaging(keywords: String): Flow<PagingData<HistoriesItem>> {
    return Pager(
            config = PagingConfig(pageSize = 5, enablePlaceholders = false),
            pagingSourceFactory = { HistoryPagingSource(apiService, keywords) })
        .flow
  }

  override suspend fun getAnalysis(productUrl: String): ResultState<AnalysisResponse> {
    return try {
      val response = apiService.getAnalysis(productUrl)
      ResultState.Success(response)
    } catch (error: HttpException) {
      ResultState.Error(error.getErrorMessage())
    }
  }

  companion object {
    @Volatile private var instance: AuthenticatedChatRepository? = null

    fun getInstance(apiService: ApiService): AuthenticatedChatRepository =
        instance
            ?: synchronized(this) {
              instance ?: AuthenticatedChatRepository(apiService).also { instance = it }
            }
  }
}
