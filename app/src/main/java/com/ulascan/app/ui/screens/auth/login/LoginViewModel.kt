package com.ulascan.app.ui.screens.auth.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ulascan.app.data.remote.UserPreferences
import com.ulascan.app.data.remote.UserRepository
import com.ulascan.app.data.remote.api.ApiConfig
import com.ulascan.app.data.remote.api.LoginRequest
import com.ulascan.app.data.remote.api.RegisterRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel(
private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Empty)
    val uiState: StateFlow<LoginUiState> = _uiState

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            try {
                _uiState.value = LoginUiState.Loading

                val response = ApiConfig.getApiService("").login(LoginRequest(email, password))

                if (response.status == true) {
                    _uiState.value = LoginUiState.Success
                    userRepository.saveToken(response.data?.token.toString())
                    Log.d("LOGIN", response.toString())
                    Log.d("LOGIN", response.data?.token.toString())
                } else {
                    _uiState.value = LoginUiState.Error("Login failed")
                }
            } catch (e: HttpException) {
                _uiState.value = LoginUiState.Error(e.message())
            } catch (e: Exception) {
                _uiState.value = LoginUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

sealed class LoginUiState {
    object Empty : LoginUiState()
    object Loading : LoginUiState()
    object Success : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}