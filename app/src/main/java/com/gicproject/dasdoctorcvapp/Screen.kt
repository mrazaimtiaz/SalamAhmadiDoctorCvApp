package com.gicproject.salamdoctorcvapp

sealed class Screen(val route: String) {
    object MainScreen: Screen("main_screen")
    object SettingScreen: Screen("setting_screen")
}
