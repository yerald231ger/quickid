package org.qid.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.qid.ui.components.ScreenHeader

@Composable
fun SearchScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize().padding(15.dp),
    ) {
        Text("Search Screen")
    }
}