package com.ulascan.app.data.repository

import android.util.Log
import com.ulascan.app.data.remote.api.ApiService
import com.ulascan.app.data.remote.response.AnalysisResponse
import com.ulascan.app.data.states.ResultState
import com.ulascan.app.utils.getErrorMessage
import retrofit2.HttpException

class AuthenticatedChatRepository(private val apiService: ApiService): ChatRepository {

    override suspend fun getAnalysis(productUrl: String): ResultState<AnalysisResponse> {
        return try {
            val response = apiService.getAnalysis(productUrl);
            ResultState.Success(response)
        } catch (error: HttpException) {
            ResultState.Error(error.getErrorMessage())
        }
    }

    companion object {
        @Volatile
        private var instance: AuthenticatedChatRepository? = null

        fun getInstance(apiService: ApiService): AuthenticatedChatRepository =
            instance ?: synchronized(this) {
                instance ?: AuthenticatedChatRepository(apiService).also { instance = it }
            }
    }
}