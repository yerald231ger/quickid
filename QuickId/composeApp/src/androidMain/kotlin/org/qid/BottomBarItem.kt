package org.qid

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomBarItem (
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    var selected: Boolean = false
)