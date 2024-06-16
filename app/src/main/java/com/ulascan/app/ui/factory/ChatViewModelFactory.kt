package com.ulascan.app.ui.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ulascan.app.di.Injection
import com.ulascan.app.ui.screens.chat.viewmodel.AuthenticatedChatViewModel
import com.ulascan.app.ui.screens.chat.ChatViewModel
import com.ulascan.app.ui.screens.chat.viewmodel.GuestChatViewModel

class ChatViewModelFactory private constructor(private val applicationContext: Context, private val isLoggedIn: Boolean) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        ChatViewModel::class.java -> {
            if(isLoggedIn) {
                AuthenticatedChatViewModel(Injection.getAuthenticatedChatRepository(applicationContext)) as T
            } else {
                GuestChatViewModel(Injection.getGuestChatRepository(applicationContext)) as T
            }
        }
        else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }

    companion object {
        @Volatile
        private var instance: ChatViewModelFactory? = null
        
        fun getInstance(context: Context, isLoggedIn: Boolean = false): ChatViewModelFactory {
            synchronized(this) {
                if (instance?.isLoggedIn != isLoggedIn) {
                    instance = null
                }
            }
            
            return instance ?: synchronized(this) {
                instance ?: ChatViewModelFactory(context.applicationContext, isLoggedIn)
            }.also { instance = it }
        }
    }
}