package com.ulascan.app.ui.screens.chat

import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ulascan.app.data.remote.response.AnalysisData
import com.ulascan.app.data.remote.response.Chat
import com.ulascan.app.data.remote.response.ResultState
import com.ulascan.app.data.repository.ChatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChatViewModel(
    private val chatRepository: ChatRepository
): ViewModel() {
    private val _uiState = MutableStateFlow<ResultState<Nothing>>(ResultState.Default)
    
    val uiState
        get() = _uiState
    
    private val _conversation = MutableStateFlow<List<Chat.Message>>(emptyList())
    val conversation: StateFlow<List<Chat.Message>>
        get() = _conversation

    private var job: Job? = null

    fun sendMessage(chat: Chat.Message) {
        job = viewModelScope.launch { 
            _uiState.emit(ResultState.Loading)
            _conversation.emit(_conversation.value.plus(chat))
            withContext(Dispatchers.IO) {
                chatRepository.getGuestAnalysis(chat.text).let { result ->
                    when (result) {
                        is ResultState.Error -> {
                            val error = result.error
                            val message = Chat.Message(
                                isResponse = true,
                                response = null,
                                text = error
                            )
                            _conversation.emit(_conversation.value.plus(message))
                        }

                        is ResultState.Success -> {
                            val response = result.data
                            val message = Chat.Message(
                                isResponse = true,
                                response = response.data,
                                text = response.message ?: "Success"
                            )
                            _conversation.emit(_conversation.value.plus(message))
                        }

                        else -> Unit
                    }
                }.also {
                    _uiState.emit(ResultState.Default)
                }   
            }
        }
    }
    
    fun cancelRequest() {
        job?.cancel()
        _uiState.value = ResultState.Default
    }
}