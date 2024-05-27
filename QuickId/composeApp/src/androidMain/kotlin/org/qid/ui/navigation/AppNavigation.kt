package org.qid.ui.navigation

import android.app.Application
import androidx.compose.animation.AnimatedContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinContext
import org.koin.compose.koinInject
import org.qid.BottomBarItem
import org.qid.ui.components.AddIdentityFileDialog
import org.qid.ui.icons.Inventory2
import org.qid.ui.screen.HomeScreen
import org.qid.ui.screen.IdentityFilesScreen
import org.qid.ui.screen.SearchScreen
import org.qid.ui.screen.SettingsScreen
import org.qid.ui.theme.QuickIdThemes
import org.qid.viewModels.IndexViewModel

@Composable
fun AppNavigation() {

    val bottomBarItems = listOf(
        BottomBarItem(
            AppScreens.HomeScreen.route,
            Icons.Filled.Home,
            Icons.Outlined.Home,
            selected = true
        ),
        BottomBarItem(
            AppScreens.SearchScreen.route,
            Icons.Default.Search,
            Icons.Default.Search
        ),
        BottomBarItem(
            AppScreens.IdentityFilesScreen.route,
            Icons.Filled.Inventory2,
            Icons.Outlined.Inventory2
        ),
        BottomBarItem(
            AppScreens.SettingsScreen.route,
            Icons.Default.MoreVert,
            Icons.Default.MoreVert
        )
    )

    val fileSelectedBottomBarItems = listOf(
        BottomBarItem(
            "Delete",
            Icons.Default.Delete,
            Icons.Default.Delete
        ),
        BottomBarItem(
            "Edit",
            Icons.Default.Edit,
            Icons.Default.Edit
        ),
        BottomBarItem(
            "Share",
            Icons.Default.Share,
            Icons.Default.Share
        )
    )


    QuickIdThemes {
        Surface {
            KoinContext {
                val scope = rememberCoroutineScope()
                val navController = rememberNavController()
                val mainViewModel = koinViewModel<IndexViewModel>()
                val snackBarHostState = remember { SnackbarHostState() }
                var showAddIdentityFileDialog by remember { mutableStateOf(false) }
                var isFileSelected by remember { mutableStateOf(false) }
                var titleTopBar by remember { mutableStateOf("Quick Id") }
                val context = koinInject<Application>()
                var filesdir = context.filesDir
                mainViewModel.selectedIdentityFile.collectAsState().value.let {
                    isFileSelected = it != null
                }

                mainViewModel.currentScreenName.collectAsState().value.let {
                    titleTopBar = it
                }

                Scaffold(
                    snackbarHost = {
                        SnackbarHost(hostState = snackBarHostState)
                    },

                    //***** BOTTOM BAR *****
                    bottomBar = {
                        AnimatedContent(
                            targetState = isFileSelected,
                            label = "Bottom bar"
                        ) {
                            BottomAppBar(
                                actions = {
                                    if (!it) {
                                        bottomBarItems.forEach { bottomBarItem ->
                                            bottomBarItem.selected =
                                                navController.currentDestination?.hierarchy?.any {
                                                    it.route == bottomBarItem.route
                                                } == true
                                            IconButton(onClick = {
                                                navController.navigate(bottomBarItem.route) {
                                                    popUpTo(navController.graph.findStartDestination().id) {
                                                        saveState = true
                                                    }
                                                    launchSingleTop = true
                                                    restoreState = true
                                                }
                                            }) {
                                                Icon(
                                                    if (bottomBarItem.selected) bottomBarItem.selectedIcon else bottomBarItem.unselectedIcon,
                                                    contentDescription = null
                                                )
                                            }
                                        }
                                    } else {
                                        fileSelectedBottomBarItems.forEach { bottomBarItem ->
                                            IconButton(onClick = {
                                                if (bottomBarItem.route == "Delete")
                                                    scope.launch {
                                                        val result = snackBarHostState.showSnackbar(
                                                            message = "Archivo ${mainViewModel.selectedIdentityFile.value?.name}",
                                                            actionLabel = "Eliminar",
                                                            withDismissAction = true
                                                        )

                                                        if (result == SnackbarResult.ActionPerformed) {
                                                            //delete file
                                                            context.deleteFile(mainViewModel.selectedIdentityFile.value!!.name)
                                                            mainViewModel.deleteFile(mainViewModel.selectedIdentityFile.value!!)

                                                        }

                                                        isFileSelected = false
                                                    }
                                            }) {
                                                Icon(
                                                    bottomBarItem.selectedIcon,
                                                    contentDescription = "Remove file",
                                                )
                                            }
                                        }
                                    }
                                },
                                floatingActionButton = {
                                    if (isFileSelected) {
                                        FloatingActionButton(
                                            onClick = {
                                                mainViewModel.setSelectedIdentityFile(null)
                                            },
                                            containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                                            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                                        ) {
                                            Icon(Icons.Default.Close, "Localized description")
                                        }
                                    } else {
                                        FloatingActionButton(
                                            onClick = { showAddIdentityFileDialog = true },
                                            containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                                            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                                        ) {
                                            Icon(Icons.Filled.Add, "Localized description")
                                        }
                                    }
                                }
                            )
                        }
                    }
                ) {
                    it.calculateTopPadding()
                    NavHost(
                        navController = navController,
                        startDestination = AppScreens.HomeScreen.route
                    ) {
                        composable(AppScreens.HomeScreen.route) {
                            HomeScreen(navController, mainViewModel)
                        }
                        composable(AppScreens.IdentityFilesScreen.route) {
                            IdentityFilesScreen(navController, mainViewModel)
                        }
                        composable(AppScreens.SettingsScreen.route) {
                            SettingsScreen(navController)
                        }
                        composable(AppScreens.SearchScreen.route) {
                            SearchScreen(navController)
                        }
                    }
                }

                if (showAddIdentityFileDialog)
                    AddIdentityFileDialog(
                        onDismissRequest = {
                            showAddIdentityFileDialog = false
                        },
                        onNewIdentityFile = {
                            mainViewModel.saveFile(it)
                            showAddIdentityFileDialog = false
                        }
                    )
            }
        }
    }
}