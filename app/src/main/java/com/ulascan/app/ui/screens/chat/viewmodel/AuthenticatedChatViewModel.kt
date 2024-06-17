package com.ulascan.app.ui.screens.chat.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ulascan.app.data.remote.response.HistoriesItem
import com.ulascan.app.data.repository.ChatRepository
import com.ulascan.app.data.repository.chat.AuthenticatedChatRepository
import com.ulascan.app.data.states.ResultState
import com.ulascan.app.ui.screens.chat.ChatViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class AuthenticatedChatViewModel(
    private val chatRepository: ChatRepository
): ChatViewModel(chatRepository) {
    private val _history = MutableStateFlow<PagingData<HistoriesItem>>(PagingData.empty())
    
    val history
        get() = _history
    
    private val _historyState = MutableStateFlow<ResultState<Nothing>>(ResultState.Default)
    val historyState
        get() = _historyState
    
    init {
        getHistory()
    }
    
    fun getHistory(keywords: String = "") {
        viewModelScope.launch {
            if (chatRepository is AuthenticatedChatRepository) {
                chatRepository.getHistoryWithPaging(keywords = keywords)
                    .distinctUntilChanged()
                    .cachedIn(viewModelScope)
                    .collect {histories ->
                        _history.emit(histories)
                    }
            }
        }
    }
}