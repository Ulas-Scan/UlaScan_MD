package com.ulascan.app.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ulascan.app.data.repository.UserRepository
import com.ulascan.app.ui.screens.auth.AuthViewModel
import com.ulascan.app.ui.screens.auth.register.LoginViewModel

class AuthenticationViewModelFactory(private val userRepository: UserRepository) :
    ViewModelProvider.Factory {
  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel> create(modelClass: Class<T>): T =
      when (modelClass) {
        LoginViewModel::class.java -> LoginViewModel(userRepository) as T
        AuthViewModel::class.java -> AuthViewModel(userRepository) as T
        else -> throw IllegalArgumentException("Unknown ViewModel class")
      }
}
