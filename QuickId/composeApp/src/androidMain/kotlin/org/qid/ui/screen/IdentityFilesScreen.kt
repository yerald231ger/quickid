package org.qid.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import infrastructure.MockFileRepository
import org.qid.ui.components.ScreenHeader
import org.qid.ui.navigation.AppScreens

@Preview
@Composable
fun IdentityFilesScreen(navController: NavController) {
    val fileRepository = MockFileRepository()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {

        Log.i("IdentityFilesScreen", "IdentityFilesScreen")
        ScreenHeader(screenTitle = "Identity Files")
        Button(
            onClick = {
                navController.navigate(AppScreens.HomeScreen.route)
            },
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text("Go to Home Screen")
        }
    }
}