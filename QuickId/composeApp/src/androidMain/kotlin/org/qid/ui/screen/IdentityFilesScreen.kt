package org.qid.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import org.qid.core.models.IdentityFile
import org.qid.infrastructure.MockFileRepository
import org.qid.ui.components.FileListItem
import org.qid.ui.components.ScreenHeader

@Composable
fun IdentityFilesScreen(navController: NavController) {
    val fileRepository = MockFileRepository()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Column {
            ScreenHeader(screenTitle = "Identity Files")
            ListIdentityFilesSection(fileRepository.getTopFiles())
        }
    }
}

@Composable
fun ListIdentityFilesSection(files: List<IdentityFile> = MockFileRepository().getTopFiles()) {
    Row {
        LazyColumn {
            items(files) { file ->
                FileListItem(identityFile = file)
            }
        }
    }
}