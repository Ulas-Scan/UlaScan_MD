package com.ulascan.app.ui.screens.chat.viewmodel

import androidx.lifecycle.viewModelScope
import com.ulascan.app.data.remote.response.HistoriesItem
import com.ulascan.app.data.repository.ChatRepository
import com.ulascan.app.data.repository.chat.AuthenticatedChatRepository
import com.ulascan.app.data.states.ResultState
import com.ulascan.app.ui.screens.chat.ChatViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AuthenticatedChatViewModel(
    private val chatRepository: ChatRepository
): ChatViewModel(chatRepository) {
    private val _history = MutableStateFlow<List<HistoriesItem>>(emptyList())
    
    val history
        get() = _history
    
    private val _historyState = MutableStateFlow<ResultState<Nothing>>(ResultState.Default)
    val historyState
        get() = _historyState
    
    init {
        getHistory()
    }
    
    fun getHistory() {
        viewModelScope.launch { 
            _historyState.emit(ResultState.Loading)
            if (chatRepository is AuthenticatedChatRepository) {
                when (val result = chatRepository.getHistory()) {
                    is ResultState.Success -> {
                        _history.emit(result.data.data.histories)
                    }
                    else -> Unit
                }.also {
                    _historyState.emit(ResultState.Default)
                }
            } else {
                _historyState.emit(ResultState.Error("Invalid repository"))
            }
        }
    }
}