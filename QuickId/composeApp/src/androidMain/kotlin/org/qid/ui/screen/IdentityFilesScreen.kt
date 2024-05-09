package org.qid.ui.screen

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

@Preview
@Composable
fun IdentityFilesScreen(navController: NavController){
    val fileRepository = MockFileRepository()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .align(Alignment.Center),
            onClick = {
                navController.navigate("home")
            }
        ){
            Text("Go to Home Screen")
        }
    }
}