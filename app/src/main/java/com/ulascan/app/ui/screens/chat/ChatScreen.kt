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
import com.ulascan.app.data.remote.response.AnalysisData
import com.ulascan.app.data.remote.response.Chat
import com.ulascan.app.data.remote.response.ResultState
import com.ulascan.app.ui.screens.chat.components.ChatContent
import com.ulascan.app.ui.screens.chat.history.Drawer
import com.ulascan.app.ui.screens.chat.history.DrawerState
import com.ulascan.app.ui.screens.chat.history.HistoryItem
import com.ulascan.app.ui.screens.chat.history.isOpened
import com.ulascan.app.ui.theme.UlaScanTheme
import com.ulascan.app.ui.theme.Weak100
import kotlin.math.roundToInt

@Composable
fun ChatScreen(uiState: ResultState<Nothing>, chat: Chat, onSendChatClickListener: (Chat.Message) -> Unit, onCancelChatClickListener: () -> Unit, onAnalyzeRouteNavigation: (AnalysisData) -> Unit, modifier: Modifier = Modifier) {
    var drawerState by remember { mutableStateOf(DrawerState.Closed) }
    
    var selectedHistoryItem by remember {
        mutableStateOf(
            HistoryItem(
                id = "chat-ebs123",
                title = "Example 1"
            )
        )
    } // Object to change

    val configuration = LocalConfiguration.current
    val density = LocalDensity.current.density

    val screenWidth = remember {
        derivedStateOf { (configuration.screenWidthDp * density).roundToInt() }
    }
    val offsetValue by remember { derivedStateOf { (screenWidth.value / 3.5).dp } }
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
                selectedNavigationItem = selectedHistoryItem,
                onNavigationItemClick = {
                    selectedHistoryItem = it
                },
                onCloseClick = {
                    drawerState = DrawerState.Closed
                },
                modifier = Modifier
                    .background(if (drawerState.isOpened()) Color.White else MaterialTheme.colorScheme.background)
            )
        }
        ChatContent(
            drawerState = drawerState,
            onDrawerClick = { drawerState = it },
            uiState = uiState,
            chat = chat,
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
        ChatScreen(uiState = ResultState.Default, chat = chat, onSendChatClickListener = { Log.d("ChatScreen", "Message sent") }, onCancelChatClickListener = { Log.d("ChatScreen", "Request cancelled") },  onAnalyzeRouteNavigation = { Log.d("ChatScreen", "Navigate to analysis screen") } )
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
        ChatScreen(uiState = ResultState.Default, chat = chat, onSendChatClickListener = { Log.d("ChatScreen", "Message sent") }, onCancelChatClickListener = { Log.d("ChatScreen", "Request cancelled") },  onAnalyzeRouteNavigation = { Log.d("ChatScreen", "Navigate to analysis screen") })
    }
}
