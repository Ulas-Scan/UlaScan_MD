package com.ulascan.app.ui.screens.chat

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
import com.ulascan.app.ui.screens.chat.components.ChatContent
import com.ulascan.app.ui.screens.chat.history.Drawer
import com.ulascan.app.ui.screens.chat.history.DrawerState
import com.ulascan.app.ui.screens.chat.history.HistoryItem
import com.ulascan.app.ui.screens.chat.history.isOpened
import com.ulascan.app.ui.theme.UlaScanTheme
import com.ulascan.app.ui.theme.Weak100
import kotlin.math.roundToInt

@Composable
fun ChatScreen(modifier: Modifier = Modifier) {
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
            modifier = Modifier
                .offset(x = animatedOffset)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ChatScreenPreview() {
    UlaScanTheme {
        ChatScreen()
    }
}