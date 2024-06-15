package com.ulascan.app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.ulascan.app.data.repository.UserRepository
import com.ulascan.app.data.remote.dataStore
import com.ulascan.app.data.remote.response.AnalysisData
import com.ulascan.app.ui.AuthViewModel
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

//                    LaunchedEffect(Unit) {
//                        val isLoggedIn = userRepository.isUserLoggedIn()
//                        startDestination.value =
//                            if (isLoggedIn) NavigationItem.Chat.route else NavigationItem.Initial.route
//                    }

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
        val authViewModel = viewModel<AuthViewModel>(factory = ViewModelFactory.getInstance(
            LocalContext.current))
        val user by authViewModel.user.collectAsState()
        
        NavHost(
            navController = navController,
            modifier = modifier,
            startDestination = startDestination,
        ) {
            composable(
                route = NavigationItem.Initial.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                }
            ) {
                InitialScreen(navController)
            }
            composable(NavigationItem.Register.route) {
                if (user.isLoggedIn) {
                    LaunchedEffect(Unit) {
                        navController.navigate(NavigationItem.Chat.route) {
                            popUpTo(NavigationItem.Register.route) { inclusive = true }
                        }
                    }
                } else {
                    val registerViewModel = viewModel<RegisterViewModel>()
                    RegisterScreen(registerViewModel, navController)
                }
            }

            composable(NavigationItem.Login.route) {
                if (user.isLoggedIn) {
                    LaunchedEffect(Unit) {
                        navController.navigate(NavigationItem.Chat.route) {
                            popUpTo(NavigationItem.Login.route) { inclusive = true }
                        }
                    }
                } else {
                    val loginViewModel: LoginViewModel =
                        viewModel(factory = LoginViewModelFactory(userRepository))
                    LoginScreen(loginViewModel, navController)
                }
            }
            
            composable(
                route = NavigationItem.Chat.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                }
            ) {
                val chatViewModel = viewModel<ChatViewModel>(factory = ViewModelFactory.getInstance(
                    LocalContext.current))

                val uiState = chatViewModel.uiState.collectAsState()
                val conversation = chatViewModel.conversation.collectAsState()

                ChatScreen(
                    uiState = uiState.value,
                    chat = Chat(
                        messages = conversation.value,
                    ),
                    isLoggedIn = user.isLoggedIn,
                    onSendChatClickListener = { message -> chatViewModel.sendMessage(message) },
                    onCancelChatClickListener = { chatViewModel.cancelRequest() },
                    onAnalyzeRouteNavigation = { 
                        data: AnalysisData -> navController.navigate(data)
                    },
                    onLoginDrawerNavigation = { navController.navigate(NavigationItem.Login.route) },
                    onRegisterDrawerNavigation = { navController.navigate(NavigationItem.Register.route) },
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