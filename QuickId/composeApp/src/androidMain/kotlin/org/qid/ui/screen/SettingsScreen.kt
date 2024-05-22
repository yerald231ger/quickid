package org.qid.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun SettingsScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Log.i("SettingScreen", "SettingScreen")
    }
}