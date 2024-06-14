package com.ulascan.app

enum class Screen {
    CHAT,
    REGISTER,
    LOGIN,
    INITIAL,
    DETAIL,
}

sealed class NavigationItem(val route: String) {
    object Chat : NavigationItem(Screen.CHAT.name)
    object Register : NavigationItem(Screen.REGISTER.name)
    object Login : NavigationItem(Screen.LOGIN.name)
    object Initial : NavigationItem(Screen.INITIAL.name)
    object DetailAnalysis : NavigationItem(Screen.DETAIL.name)
}
