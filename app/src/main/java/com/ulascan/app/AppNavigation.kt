package com.ulascan.app

enum class Screen {
    CHAT
}

sealed class NavigationItem(val route: String) {
    object Chat : NavigationItem(Screen.CHAT.name)
}
