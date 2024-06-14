package com.ulascan.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ulascan.app.data.remote.response.Chat
import com.ulascan.app.ui.ViewModelFactory
import com.ulascan.app.data.remote.UserPreferences
import com.ulascan.app.data.remote.UserRepository
import com.ulascan.app.data.remote.dataStore
import com.ulascan.app.data.remote.response.AnalysisData
import com.ulascan.app.ui.screens.auth.LoginViewModelFactory
import com.ulascan.app.ui.screens.auth.login.LoginScreen
import com.ulascan.app.ui.screens.auth.register.LoginViewModel
import com.ulascan.app.ui.screens.auth.register.RegisterScreen
import com.ulascan.app.ui.screens.auth.register.RegisterViewModel
import com.ulascan.app.ui.screens.chat.ChatScreen
import com.ulascan.app.ui.screens.chat.ChatViewModel
import com.ulascan.app.ui.screens.detailAnalysis.DetailAnalysisScreen
import com.ulascan.app.ui.screens.initial.InitialScreen
import com.ulascan.app.ui.theme.UlaScanTheme
import kotlin.reflect.typeOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userPreferences = UserPreferences.getInstance(dataStore)
        val userRepository = UserRepository(userPreferences)

        setContent {
            UlaScanTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val startDestination = remember { mutableStateOf(NavigationItem.Initial.route) }

                    LaunchedEffect(Unit) {
                        val isLoggedIn = userRepository.isUserLoggedIn()
                        startDestination.value =
                            if (isLoggedIn) NavigationItem.Chat.route else NavigationItem.Initial.route
                    }

                    AppNavHost(
                        navController = navController,
                        userRepository,
                        startDestination = startDestination.value
                    )
                }
            }
        }
    }

    @Composable
    fun AppNavHost(
        navController: NavHostController,
        userRepository: UserRepository,
        modifier: Modifier = Modifier,
        startDestination: String
    ) {
        NavHost(
            navController = navController,
            modifier = modifier,
            startDestination = startDestination,
        ) {
            composable(NavigationItem.Initial.route) {
                InitialScreen(navController)
            }
            composable(NavigationItem.Register.route) {
                val registerViewModel = viewModel<RegisterViewModel>()
                RegisterScreen(registerViewModel, navController)
            }

            composable(NavigationItem.Login.route) {
                val loginViewModel: LoginViewModel =
                    viewModel(factory = LoginViewModelFactory(userRepository))
                LoginScreen(loginViewModel, navController)
            }
            composable(NavigationItem.Chat.route) {
                val chatViewModel = viewModel<ChatViewModel>(factory = ViewModelFactory.getInstance(
                    LocalContext.current))

                val uiState = chatViewModel.uiState.collectAsState()
                val conversation = chatViewModel.conversation.collectAsState()

                ChatScreen(
                    uiState = uiState.value,
                    chat = Chat(
                        messages = conversation.value,
                    ),
                    onSendChatClickListener = { message -> chatViewModel.sendMessage(message) },
                    onCancelChatClickListener = { chatViewModel.cancelRequest() },
                    onAnalyzeRouteNavigation = { 
                        data: AnalysisData -> navController.navigate(data)
                    } 
                )
            }
            composable<AnalysisData> (
                typeMap = mapOf(typeOf<Double>() to DoubleType)
            ){ backStackEntry ->
                val data: AnalysisData = backStackEntry.toRoute()
                
                DetailAnalysisScreen(navController, data)
            }
        }
    }
}