package org.qid.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.qid.BottomBarItem
import org.qid.R
import org.qid.ui.screen.HomeScreen
import org.qid.ui.screen.IdentityFilesScreen
import org.qid.ui.screen.SearchScreen
import org.qid.ui.theme.QuickIdThemes

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
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
            Scaffold(
                bottomBar = {
                    NavigationBar {
                        bottomBarItems.forEachIndexed { index, item ->
                            NavigationBarItem(
                                selected = selectedItemIndex == index,
                                onClick = {
                                    if (selectedItemIndex != index) {
                                        selectedItemIndex = index
                                        navController.navigate(item.route)
                                    }
                                },
                                icon = {
                                    BadgedBox(
                                        badge = {
                                            if (item.hasNews) {
                                                Badge()
                                            }
                                        }
                                    ) {
                                        Icon(
                                            imageVector = if (selectedItemIndex == index) item.selectedIcon else item.unselectedIcon,
                                            contentDescription = item.title
                                        )
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
                        SearchScreen(navController)
                    }
                }
            }
        }
    }
}