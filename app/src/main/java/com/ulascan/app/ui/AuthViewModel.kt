package com.ulascan.app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ulascan.app.data.remote.UserModel
import com.ulascan.app.data.repository.UserRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class AuthViewModel(private val repository: UserRepository) : ViewModel() {
    val user: StateFlow<UserModel> = repository.getUserSession().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = UserModel("", false)
    )
}
