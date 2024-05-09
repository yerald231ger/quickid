package org.qid.ui.navigation

sealed class AppScreens(val route: String) {
    data object HomeScreen: AppScreens("home_screen")
    data object SettingsScreen: AppScreens("settings_screen")
    data object IdentityFilesScreen: AppScreens("identity_files_screen")
    data object SearchScreen: AppScreens("search_screen")
}