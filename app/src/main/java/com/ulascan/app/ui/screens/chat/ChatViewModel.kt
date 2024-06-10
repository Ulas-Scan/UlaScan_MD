package com.ulascan.app.ui.screens.chat

import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatViewModel: ViewModel() {
    
    // TODO: Use flow instead!
    private val _conversation = MutableStateFlow<List<Chat.Message>>(emptyList())
    val conversation: StateFlow<List<Chat.Message>>
        get() = _conversation
    
    private val responses = mutableListOf(
        "The Martian rover discovered a new type of mineral that glows in the dark and emits a faint, pleasant aroma of vanilla.",
        "A team of archaeologists recently uncovered a 5,000-year-old smartphone in the ruins of an ancient city, complete with a functioning battery.",
        "Scientists have successfully taught a colony of ants to solve simple mathematical equations, marking a breakthrough in interspecies communication",
        "A rare breed of bioluminescent birds has been spotted in the Amazon rainforest, their feathers glowing in a mesmerizing array of colors at night.",
        "A new culinary trend is sweeping through Europe: edible holographic food, which projects stunning 3D images before being consumed."
    )
    
    fun sendMessage(chat: Chat.Message) {
        viewModelScope.launch { 
            _conversation.emit(_conversation.value.plus(chat))
            if (!chat.isResponse) {
                val response = Chat.Message(responses.random(), true)
                _conversation.emit(_conversation.value.plus(response))
            }
        }
    }
}

// TODO: Refactor this
data class Chat(
    val chatId: String,
    val messages: List<Message>
) {
    data class Message (
        val text: String,
        val isResponse: Boolean,
    )
}