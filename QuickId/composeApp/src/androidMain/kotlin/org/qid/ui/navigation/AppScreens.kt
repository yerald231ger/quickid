package org.qid.ui.navigation

sealed class AppScreens(val route: String) {
    data object HomeScreen: AppScreens("home")
    data object SettingsScreen: AppScreens("settings")
    data object IdentityFilesScreen: AppScreens("identity_files")
    data object SearchScreen: AppScreens("search")
}