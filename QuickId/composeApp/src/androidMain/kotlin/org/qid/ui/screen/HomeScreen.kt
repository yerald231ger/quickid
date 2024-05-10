package org.qid.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import core.infrastructure.FileRepository
import core.models.IdentityFile
import infrastructure.MockFileRepository
import org.qid.R
import org.qid.ui.components.FileItem
import org.qid.ui.components.TitleContainer
import org.qid.ui.navigation.AppScreens

@Composable
fun HomeScreen(navController: NavController) {
    val fileRepository: FileRepository = MockFileRepository()
    Box(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface).fillMaxSize()
    ) {
        Column {
            HelloSection(onClickSearch = {
                navController.navigate(AppScreens.IdentityFilesScreen.route)
            })
            QuickIdentityFilesSection(fileRepository.getTopFiles(), onClickEdit = {
                navController.navigate(AppScreens.IdentityFilesScreen.route)
            })
            RecentIdentityFileSection(fileRepository.getTopFiles())
        }
    }
}

@Preview
@Composable
fun HelloSection(
    name: String = "Gerardo", onClickSearch: () -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                color = MaterialTheme.colorScheme.onSurface,
                text = stringResource(R.string.hello) + " $name",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                color = MaterialTheme.colorScheme.onSurface,
                text = stringResource(R.string.what_are_we_searching_for),
                style = MaterialTheme.typography.bodySmall
            )
        }
        Box(
            modifier = Modifier.border(
                1.dp, MaterialTheme.colorScheme.outline, MaterialTheme.shapes.medium
            ).size(36.dp), contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(16.dp).clickable {
                        onClickSearch()
                    },
                painter = painterResource(R.drawable.ic_search),
                contentDescription = "Search",
                tint = MaterialTheme.colorScheme.onSurface
            )
        }

    }
}

@Composable
fun RecentIdentityFileSection(files: List<IdentityFile>, onClickEdit: () -> Unit = {}) {
    Row {
        TitleContainer(
            title = stringResource(R.string.import_identity_file), label = "- Clear"
        ) {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(files) { _, file ->
                    FileItem(identityFile = file)
                }
            }
        }
    }
}

@Composable
fun QuickIdentityFilesSection(files: List<IdentityFile>, onClickEdit: () -> Unit) {
    Row {
        TitleContainer(
            title = stringResource(R.string.quick_access_files), label = "+ Add"
        ) {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(files) { _, file ->
                    FileItem(identityFile = file)
                }
            }
        }
    }
}