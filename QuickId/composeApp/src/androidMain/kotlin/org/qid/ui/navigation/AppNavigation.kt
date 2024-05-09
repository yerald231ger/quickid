package org.qid.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.qid.BottomBarItem
import org.qid.ui.icons.Description
import org.qid.ui.screen.HomeScreen
import org.qid.ui.screen.IdentityFilesScreen
import org.qid.ui.screen.SettingsScreen
import org.qid.ui.theme.QuickIdThemes

@Composable
fun AppNavigation() {
    val bottomBarItems = listOf(
        BottomBarItem(
            AppScreens.HomeScreen.route,
            "Home",
            Icons.Filled.Home,
            Icons.Outlined.Home,
            false
        ),
        BottomBarItem(
            AppScreens.IdentityFilesScreen.route,
            "Files",
            Icons.Filled.Description,
            Icons.Outlined.Description,
            false
        ),
        BottomBarItem(
            AppScreens.SettingsScreen.route,
            "Settings",
            Icons.Filled.Settings,
            Icons.Outlined.Settings,
            false
        ),
    )

    QuickIdThemes {
        Surface {
            val navController = rememberNavController()
            Scaffold(
                bottomBar = {
                    NavigationBar {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination
                        bottomBarItems.forEach { item ->
                            NavigationBarItem(
                                icon = {
                                    if (currentDestination?.hierarchy?.any { it.route == item.route } == true) {
                                        Icon(item.selectedIcon, contentDescription = null)
                                    } else {
                                        Icon(item.unselectedIcon, contentDescription = null)
                                    }
                                },
                                label = {
                                    Text(item.title)
                                },
                                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                                onClick = {
                                    navController.navigate(item.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                            )
                        }
                    }
                }
            ) {
                it.calculateTopPadding()
                NavHost(
                    navController = navController,
                    startDestination = AppScreens.HomeScreen.route
                ) {
                    composable(AppScreens.HomeScreen.route) {
                        HomeScreen(navController)
                    }
                    composable(AppScreens.IdentityFilesScreen.route) {
                        IdentityFilesScreen(navController)
                    }
                    composable(AppScreens.SettingsScreen.route) {
                        SettingsScreen(navController)
                    }
                }
            }
        }
    }
}