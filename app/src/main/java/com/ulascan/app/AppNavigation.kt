package com.ulascan.app

enum class Screen {
    CHAT,
    LOGIN,
    REGISTER,
}

sealed class NavigationItem(val route: String) {
    object Chat : NavigationItem(Screen.CHAT.name)
    object Register : NavigationItem(Screen.REGISTER.name)

    object Login : NavigationItem(Screen.LOGIN.name)

}
