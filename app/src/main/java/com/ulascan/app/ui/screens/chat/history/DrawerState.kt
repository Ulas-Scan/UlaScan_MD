package com.ulascan.app.ui.screens.chat.history

enum class DrawerState {
    Opened,
    Closed
}

fun DrawerState.isOpened(): Boolean {
    return this.name == "Opened"
}

fun DrawerState.opposite(): DrawerState {
    return when (this.name) {
        "Opened" -> DrawerState.Closed
        else -> DrawerState.Opened
    }
}