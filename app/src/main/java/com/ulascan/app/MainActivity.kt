package com.ulascan.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ulascan.app.ui.screens.chat.Chat
import com.ulascan.app.ui.screens.chat.ChatScreen
import com.ulascan.app.ui.screens.chat.ChatViewModel
import com.ulascan.app.ui.screens.auth.register.RegisterScreen
import com.ulascan.app.ui.screens.auth.register.RegisterViewModel
import com.ulascan.app.ui.theme.UlaScanTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UlaScanTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavHost(navController = rememberNavController())
                }
            }
        }
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
//    startDestination: String = NavigationItem.Chat.route,
    startDestination: String = NavigationItem.Register.route
) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = startDestination,
    ) {
        composable(NavigationItem.Register.route) {
            val registerViewModel = viewModel<RegisterViewModel>()

            RegisterScreen(registerViewModel, navController)
        }
        composable(NavigationItem.Login.route){
        }
        composable(NavigationItem.Chat.route) {
            val chatViewModel = viewModel<ChatViewModel>()
            val conversation = chatViewModel.conversation.collectAsState()

            ChatScreen(
                chat = Chat(
                    messages = conversation.value,
                    chatId = "chat-ebs123",
                ),
                onSendChatClickListener = { message -> chatViewModel.sendMessage(message) }
            )
        }
    }
}