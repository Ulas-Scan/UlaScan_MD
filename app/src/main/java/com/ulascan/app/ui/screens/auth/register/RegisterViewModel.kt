package com.ulascan.app.ui.screens.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ulascan.app.data.remote.api.ApiConfig
import com.ulascan.app.data.remote.request.RegisterRequest
import com.ulascan.app.data.states.RegisterUiState
import com.ulascan.app.utils.getErrorMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RegisterViewModel : ViewModel() {

  private val _uiState = MutableStateFlow<RegisterUiState>(RegisterUiState.Empty)
  val uiState: StateFlow<RegisterUiState> = _uiState

  fun registerUser(name: String, email: String, password: String) {
    viewModelScope.launch {
      try {
        _uiState.value = RegisterUiState.Loading

        val response = ApiConfig.getApiService("").register(RegisterRequest(name, email, password))

        if (response.status == true) {
          _uiState.value = RegisterUiState.Success
        } else {
          _uiState.value = RegisterUiState.Error("Registration failed")
        }
      } catch (e: HttpException) {
        _uiState.value = RegisterUiState.Error(e.getErrorMessage())
      } catch (e: Exception) {
        _uiState.value = RegisterUiState.Error(e.message ?: "Unknown error")
      }
    }
  }
}
