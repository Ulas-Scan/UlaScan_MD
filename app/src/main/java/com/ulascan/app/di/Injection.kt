package com.ulascan.app.di

import com.ulascan.app.data.remote.api.ApiConfig
import com.ulascan.app.data.repository.ChatRepository

object Injection {
    fun getChatRepository(): ChatRepository {
        val dummyToken = ""
        val apiService = ApiConfig.getApiService(dummyToken)
        
        return ChatRepository.getInstance(apiService)
    }
}