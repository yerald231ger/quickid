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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import org.qid.core.models.IdentityFile
import org.qid.ui.components.FileListItem
import org.qid.ui.components.ScreenHeader
import org.qid.viewModels.IndexViewModel

@Composable
fun IdentityFilesScreen(
    navController: NavController,
    viewModel: IndexViewModel
) {
    val identityFileUiState by viewModel.identityFileUiState.collectAsState()
    viewModel.setCurrentScreenName("Files")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Column {
            ScreenHeader("All files")
            ListIdentityFilesSection(identityFileUiState.identityFiles)
            {
                viewModel.setSelectedIdentityFile(it)
            }
        }
    }
}

@Composable
fun ListIdentityFilesSection(
    files: List<IdentityFile> = emptyList(),
    onClick: (identityFile: IdentityFile) -> Unit = {}
) {
    Row {
        LazyColumn {
            items(files) { file ->
                FileListItem(identityFile = file, onClick)
            }
        }
    }
}