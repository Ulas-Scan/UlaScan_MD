package com.ulascan.app.data.repository

import com.ulascan.app.data.remote.UserModel
import com.ulascan.app.data.remote.UserPreferences
import com.ulascan.app.data.remote.api.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull


class UserRepository(private val userPreferences: UserPreferences) {

    suspend fun saveToken(token: String){
        userPreferences.saveToken(token)
    }
    
    suspend fun clearToken() {
        userPreferences.clearToken()
    }

    fun getUserSession(): Flow<UserModel> {
        return userPreferences.getUserSession()
    }
}