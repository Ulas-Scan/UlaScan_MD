package com.ulascan.app.data.repository

import com.ulascan.app.data.remote.api.ApiService
import com.ulascan.app.data.remote.response.AnalysisResponse
import com.ulascan.app.data.remote.response.ResultState
import com.ulascan.app.utils.getErrorMessage
import retrofit2.HttpException

class ChatRepository(private val apiService: ApiService) {
    fun test(): String {
        return "test"
    }

    suspend fun getGuestAnalysis(productUrl: String): ResultState<AnalysisResponse> {
        return try {
            val response = apiService.getGuestAnalysis(productUrl);
            ResultState.Success(response)
        } catch (error: HttpException) {
            ResultState.Error(error.getErrorMessage())
        }
    }

    companion object {
        @Volatile
        private var instance: ChatRepository? = null

        fun getInstance(apiService: ApiService): ChatRepository =
            instance ?: synchronized(this) {
                instance ?: ChatRepository(apiService).also { instance = it }
            }
    }
}