package com.ulascan.app.data.remote

import kotlinx.coroutines.flow.Flow


class UserRepository(private val userPreferences: UserPreferences) {

    suspend fun saveToken(token: String){
        userPreferences.saveToken(token)
    }

    fun getToken(): Flow<String?> {
        return userPreferences.getToken()
    }

    suspend fun clearToken() {
        userPreferences.clearToken()
    }

}