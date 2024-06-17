package com.ulascan.app.ui.screens.chat

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.ulascan.app.NavigationItem
import com.ulascan.app.data.remote.response.AnalysisData
import com.ulascan.app.data.remote.response.Chat
import com.ulascan.app.data.remote.response.HistoriesItem
import com.ulascan.app.data.remote.response.UserResponse
import com.ulascan.app.data.states.ResultState
import com.ulascan.app.ui.screens.chat.components.ChatContent
import com.ulascan.app.ui.screens.chat.history.Drawer
import com.ulascan.app.ui.screens.chat.history.DrawerState
import com.ulascan.app.ui.screens.chat.history.isOpened
import com.ulascan.app.ui.theme.UlaScanTheme
import com.ulascan.app.ui.theme.Weak100
import kotlinx.coroutines.flow.flowOf
import kotlin.math.roundToInt

@Composable
fun ChatScreen(
    authState: ResultState<UserResponse>,
    uiState: ResultState<Nothing>,
    historyState: ResultState<Nothing>,
    chat: Chat,
    history: LazyPagingItems<HistoriesItem>,
    isLoggedIn: Boolean = false,
    onFetchHistory: () -> Unit,
    onSendChatClickListener: (Chat.Message) -> Unit,
    onCancelChatClickListener: () -> Unit,
    onAnalyzeRouteNavigation: (AnalysisData) -> Unit,
    onLoginDrawerNavigation: () -> Unit,
    onRegisterDrawerNavigation: () -> Unit,
    modifier: Modifier = Modifier
) {
    var drawerState by remember { mutableStateOf(DrawerState.Closed) }
    
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current.density

    val screenWidth = remember {
        derivedStateOf { (configuration.screenWidthDp * density).roundToInt() }
    }
    val offsetValue by remember { derivedStateOf { (screenWidth.value / 3.6).dp } }
    val animatedOffset by animateDpAsState(
        targetValue = if (drawerState.isOpened()) offsetValue else 0.dp,
        label = "Animated Offset"
    )
    
    Box(
        modifier = modifier
            .background(Weak100)
            .fillMaxSize(),
    ) {
        if (drawerState.isOpened()) {
            Drawer(
                authState = authState,
                historyState = historyState,
                history = history,
                isLoggedIn = isLoggedIn,
                onAnalyzeRouteNavigation = onAnalyzeRouteNavigation,
                onLoginDrawerNavigation = onLoginDrawerNavigation,
                onRegisterDrawerNavigation = onRegisterDrawerNavigation,
                modifier = Modifier
                    .background(if (drawerState.isOpened()) Color.White else MaterialTheme.colorScheme.background)
            )
        }
        ChatContent(
            drawerState = drawerState,
            onDrawerClick = { drawerState = it },
            uiState = uiState,
            chat = chat,
            onFetchHistory = onFetchHistory,
            onSendChatClickListener = onSendChatClickListener,
            onCancelChatClickListener = onCancelChatClickListener,
            onAnalyzeRouteNavigation = onAnalyzeRouteNavigation,
            modifier = Modifier
                .offset(x = animatedOffset)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ChatScreenPreview() {
    UlaScanTheme {
        val chat = Chat(
            messages = emptyList()
        )
        ChatScreen(
            authState = ResultState.Default,
            uiState = ResultState.Default,
            historyState = ResultState.Default,
            chat = chat,
            history = flowOf(PagingData.from(emptyList<HistoriesItem>())).collectAsLazyPagingItems(),
            onFetchHistory = { Log.d("ChatScreen", "Fetch history") }, 
            onSendChatClickListener = { Log.d("ChatScreen", "Message sent") }, 
            onCancelChatClickListener = { Log.d("ChatScreen", "Request cancelled") },  
            onAnalyzeRouteNavigation = { Log.d("ChatScreen", "Navigate to analysis screen") },
            onLoginDrawerNavigation = { Log.d("ChatScreen", "Navigate to login screen") },
            onRegisterDrawerNavigation = { Log.d("ChatScreen", "Navigate to register screen") }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ChatScreenWithMessagePreview() {
    val num = 5  // Number of items to create
    val messages = mutableListOf<Chat.Message>()

    for (i in 1..num) {
        val message = Chat.Message(
            isResponse = i%2 == 0,
            text = "Message number $i"
        )
        messages.add(message)
    }
    
    UlaScanTheme {
        val chat = Chat(
            messages = messages
        )
        ChatScreen(
            authState = ResultState.Default,
            uiState = ResultState.Default,
            historyState = ResultState.Default,
            chat = chat,
            history = flowOf(PagingData.from(emptyList<HistoriesItem>())).collectAsLazyPagingItems(),
            onFetchHistory = { Log.d("ChatScreen", "Fetch history") },
            onSendChatClickListener = { Log.d("ChatScreen", "Message sent") }, 
            onCancelChatClickListener = { Log.d("ChatScreen", "Request cancelled") },  
            onAnalyzeRouteNavigation = { Log.d("ChatScreen", "Navigate to analysis screen") },
            onLoginDrawerNavigation = { Log.d("ChatScreen", "Navigate to login screen") },
            onRegisterDrawerNavigation = { Log.d("ChatScreen", "Navigate to register screen") }
        )
    }
}
