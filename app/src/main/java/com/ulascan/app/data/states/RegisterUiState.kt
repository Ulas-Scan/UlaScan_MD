package com.ulascan.app.data.states

sealed class RegisterUiState {
  object Empty : RegisterUiState()

  object Loading : RegisterUiState()

  object Success : RegisterUiState()

  data class Error(val message: String) : RegisterUiState()
}
