package org.qid.ui.navigation

import androidx.compose.animation.AnimatedContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinContext
import org.qid.ui.components.AddIdentityFileDialog
import org.qid.ui.screen.HomeScreen
import org.qid.ui.screen.IdentityFilesScreen
import org.qid.ui.screen.SettingsScreen
import org.qid.ui.theme.QuickIdThemes
import org.qid.viewModels.IndexViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {

    QuickIdThemes {
        Surface {
            KoinContext {
                val navController = rememberNavController()
                val mainViewModel = koinViewModel<IndexViewModel>()
                var showAddIdentityFileDialog by remember { mutableStateOf(false) }
                var titleTopBar by remember { mutableStateOf("Quick Id") }
                val scrollBehavior =
                    TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
                var isFileSelected by remember { mutableStateOf(false) }
                mainViewModel.selectedIdentityFile.collectAsState().value?.let {
                    isFileSelected = true
                }

                navController.addOnDestinationChangedListener { _, destination, _ ->
                    titleTopBar = when (destination.route) {
                        AppScreens.HomeScreen.route -> "Quick Id"
                        AppScreens.IdentityFilesScreen.route -> "Identity Files"
                        AppScreens.SettingsScreen.route -> "Settings"
                        else -> "Quick Id"
                    }
                }

                Scaffold(

                    //***** TOP BAR *****
                    topBar = {
                        CenterAlignedTopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.surfaceContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text(
                                    titleTopBar,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            },
                            navigationIcon = {
                                IconButton(onClick = {
                                    navController.popBackStack()
                                }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Localized description"
                                    )
                                }
                            },
                            actions = {
                                IconButton(onClick = {
                                    navController.navigate(AppScreens.SearchScreen.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }) {
                                    Icon(
                                        imageVector = Icons.Filled.Search,
                                        contentDescription = "Search files"
                                    )
                                }
                                IconButton(onClick = {
                                    navController.navigate(AppScreens.IdentityFilesScreen.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                })
                                {
                                    Icon(
                                        imageVector = Icons.Filled.MoreVert,
                                        contentDescription = "More options"
                                    )
                                }
                            },
                            scrollBehavior = scrollBehavior,
                        )
                    },

                    //***** BOTTOM BAR *****
                    bottomBar = {
                        AnimatedContent(
                            targetState = isFileSelected,
                            label = "Bottom bar"
                        ) {
                            BottomAppBar(
                                actions = {
                                    if (it) {
                                        IconButton(onClick = {
                                        }) {
                                            Icon(
                                                Icons.Default.Home,
                                                contentDescription = "Home screen"
                                            )
                                        }
                                    } else {
                                        IconButton(onClick = {

                                        }) {
                                            Icon(
                                                Icons.Default.Delete,
                                                contentDescription = "Remove file",
                                            )
                                        }
                                        IconButton(onClick = {

                                        }) {
                                            Icon(
                                                Icons.Default.Edit,
                                                contentDescription = "Edit file"
                                            )
                                        }
                                        IconButton(onClick = {

                                        }) {
                                            Icon(
                                                Icons.Default.Share,
                                                contentDescription = "Share file",
                                            )
                                        }
                                    }

                                },
                                floatingActionButton = {
                                    if (isFileSelected) {
                                        FloatingActionButton(
                                            onClick = { isFileSelected = false },
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
                    }
                }

                if (showAddIdentityFileDialog)
                    AddIdentityFileDialog(
                        onDismissRequest = {
                            showAddIdentityFileDialog = false
                        },
                        mainViewModel::saveFile
                    )
            }
        }
    }
}