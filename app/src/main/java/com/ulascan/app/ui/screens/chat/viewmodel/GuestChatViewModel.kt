package com.ulascan.app.ui.screens.chat.viewmodel

import com.ulascan.app.data.repository.ChatRepository
import com.ulascan.app.ui.screens.chat.ChatViewModel

class GuestChatViewModel(private val chatRepository: ChatRepository) :
    ChatViewModel(chatRepository) {}
