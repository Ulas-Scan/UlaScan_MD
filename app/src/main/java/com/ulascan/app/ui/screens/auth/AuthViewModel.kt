package com.ulascan.app.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ulascan.app.data.remote.UserModel
import com.ulascan.app.data.remote.response.UserResponse
import com.ulascan.app.data.repository.UserRepository
import com.ulascan.app.data.states.ResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {
    private val _user: MutableStateFlow<UserModel> = MutableStateFlow(UserModel("", false))
    val user: StateFlow<UserModel> get() = _user

    private val _uiState = MutableStateFlow<ResultState<UserResponse>>(ResultState.Default)

    val uiState
        get() = _uiState
    
    init {
        viewModelScope.launch { 
            repository.getUserSession().collect { user ->
                _user.emit(user)
            }
        }
    }
    
    fun getUserInformation(token: String) {
        viewModelScope.launch {
            uiState.emit(ResultState.Loading)
            val state = repository.getUserInformation(token)

            if (state is ResultState.Error) {
                _user.value = UserModel("", false)
            }
            
            uiState.emit(state)
        }
    }
    
    fun logout() {
        viewModelScope.launch { 
            repository.logout()
            _user.value = UserModel("", false)
        }
    }
}
