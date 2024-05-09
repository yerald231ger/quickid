package org.qid.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import org.qid.BottomBarItem
import org.qid.R
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
            ImageVector.vectorResource(R.drawable.ic_inventory),
            ImageVector.vectorResource(R.drawable.ic_inventory),
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

    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

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
                                    Icon(item.selectedIcon, contentDescription = null)
                                },
                                label = {
                                    Text(item.title)
                                },
                                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                                onClick = {
                                    navController.navigate(item.route) {
                                        // Pop up to the start destination of the graph to
                                        // avoid building up a large stack of destinations
                                        // on the back stack as users select items
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        // Avoid multiple copies of the same destination when
                                        // reselecting the same item
                                        launchSingleTop = true
                                        // Restore state when reselecting a previously selected item
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