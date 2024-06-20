package com.ulascan.app.data.repository

import com.ulascan.app.data.remote.UserModel
import com.ulascan.app.data.remote.UserPreferences
import com.ulascan.app.data.remote.api.ApiService
import com.ulascan.app.data.remote.response.UserResponse
import com.ulascan.app.data.states.ResultState
import com.ulascan.app.utils.getErrorMessage
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException

class UserRepository(
    private val userPreferences: UserPreferences,
    private val apiService: ApiService
) {

  suspend fun saveToken(token: String) {
    userPreferences.saveToken(token)
  }

  fun getUserSession(): Flow<UserModel> {
    return userPreferences.getUserSession()
  }

  suspend fun logout() {
    userPreferences.clearToken()
  }

  suspend fun getUserInformation(token: String): ResultState<UserResponse> {
    return try {
      val response = apiService.getUserInformation("Bearer $token")
      ResultState.Success(response)
    } catch (error: HttpException) {
      userPreferences.clearToken()
      ResultState.Error(error.getErrorMessage())
    } catch (error: IOException) {
      ResultState.Error("No internet connection. Please check your network and try again.")
    } catch (error: Exception) {
      ResultState.Error("An unexpected error occurred: ${error.localizedMessage}")
    }
  }
}
