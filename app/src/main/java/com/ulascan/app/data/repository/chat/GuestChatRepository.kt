package com.ulascan.app.data.repository.chat

import com.ulascan.app.data.remote.api.ApiService
import com.ulascan.app.data.remote.response.AnalysisResponse
import com.ulascan.app.data.repository.ChatRepository
import com.ulascan.app.data.states.ResultState
import com.ulascan.app.utils.getErrorMessage
import retrofit2.HttpException

class GuestChatRepository(private val apiService: ApiService): ChatRepository {

    override suspend fun getAnalysis(productUrl: String): ResultState<AnalysisResponse> {
        return try {
            val response = apiService.getGuestAnalysis(productUrl)
            ResultState.Success(response)
        } catch (error: HttpException) {
            ResultState.Error(error.getErrorMessage())
        }
    }

    companion object {
        @Volatile
        private var instance: GuestChatRepository? = null

        fun getInstance(apiService: ApiService): GuestChatRepository =
            instance ?: synchronized(this) {
                instance ?: GuestChatRepository(apiService).also { instance = it }
            }
    }
}
