package com.ulascan.app.di

import android.content.Context
import com.ulascan.app.data.remote.UserModel
import com.ulascan.app.data.remote.UserPreferences
import com.ulascan.app.data.remote.api.ApiConfig
import com.ulascan.app.data.remote.api.ApiService
import com.ulascan.app.data.remote.dataStore
import com.ulascan.app.data.repository.ChatRepository
import com.ulascan.app.data.repository.chat.AuthenticatedChatRepository
import com.ulascan.app.data.repository.chat.GuestChatRepository
import com.ulascan.app.data.repository.UserRepository
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking

object Injection {
    private fun getToken(context: Context): String {
        val userPreferences = UserPreferences.getInstance(context.dataStore)
        
        return runBlocking {
            (userPreferences.getUserSession().firstOrNull() ?: UserModel("", false)).token
        }
    }
    
    private fun getApiService(context: Context): ApiService {
        val token = getToken(context)
        
        return ApiConfig.getApiService(token)
    }
    
    fun getGuestChatRepository(context: Context): ChatRepository {
        return GuestChatRepository.getInstance(getApiService(context))
    }
    
    fun getAuthenticatedChatRepository(context: Context): ChatRepository {
        return AuthenticatedChatRepository.getInstance(getApiService(context))
    }
    
    fun getUserRepository(context: Context): UserRepository {
        val userPreferences = UserPreferences.getInstance(context.dataStore)
        val apiService = getApiService(context)
        
        return UserRepository(userPreferences, apiService)
    }
}