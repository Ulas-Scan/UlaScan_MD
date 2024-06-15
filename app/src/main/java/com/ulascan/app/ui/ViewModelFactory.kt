package com.ulascan.app.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ulascan.app.di.Injection
import com.ulascan.app.ui.screens.chat.ChatViewModel

class ViewModelFactory private constructor(private val applicationContext: Context) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        ChatViewModel::class.java -> ChatViewModel(Injection.getChatRepository(applicationContext)) as T
        AuthViewModel::class.java -> AuthViewModel(Injection.getUserRepository(applicationContext)) as T
        else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(context.applicationContext)
            }.also { instance = it }
    }
}