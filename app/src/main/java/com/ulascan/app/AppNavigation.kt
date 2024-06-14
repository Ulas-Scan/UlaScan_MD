package com.ulascan.app

import android.os.Bundle
import androidx.navigation.NavType

enum class Screen {
    CHAT,
    REGISTER,
    LOGIN,
    INITIAL,
}

sealed class NavigationItem(val route: String) {
    object Chat : NavigationItem(Screen.CHAT.name)
    object Register : NavigationItem(Screen.REGISTER.name)
    object Login : NavigationItem(Screen.LOGIN.name)

    object Initial : NavigationItem(Screen.INITIAL.name)
}

val DoubleType = object : NavType<Double>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Double {
        return bundle.getDouble(key)
    }

    override fun parseValue(value: String): Double {
        return value.toDouble()
    }

    override fun serializeAsValue(value: Double): String {
        return value.toString()
    }

    override fun put(bundle: Bundle, key: String, value: Double) {
        bundle.putDouble(key, value)
    }
}