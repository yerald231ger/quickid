package org.qid.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import core.infrastructure.FileRepository
import infrastructure.MockFileRepository
import org.qid.R
import org.qid.ui.components.FileItem
import org.qid.ui.navigation.AppScreens

@Composable
fun HomeScreen(navController: NavController) {
    var fileRepository: FileRepository = MockFileRepository()
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxSize()
    ) {
        Column {
            HelloSection(onClickSearch = {
                navController.navigate(AppScreens.IdentityFilesScreen.route)
            })
            QuickIdentityFilesSection(fileRepository.getTopFiles(),
                onClickEdit = {
                    navController.navigate(AppScreens.IdentityFilesScreen.route)
                })
        }
    }
}

@Preview
@Composable
fun HelloSection(
    name: String = "Gerardo",
    onClickSearch: () -> Unit = {}
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
                1.dp,
                MaterialTheme.colorScheme.outline,
                MaterialTheme.shapes.medium
            )
                .size(36.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(16.dp)
                    .clickable {
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
fun QuickIdentityFilesSection(files: List<core.models.IdentityFile>, onClickEdit: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(15.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .align(Alignment.TopStart),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    text = stringResource(R.string.quick_access_files),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
                IconButton(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    onClick = {
                        onClickEdit()
                    },
                    content = {
                        Icon(
                            Icons.Rounded.Edit,
                            tint = MaterialTheme.colorScheme.onSurface,
                            contentDescription = "Modify list",
                            modifier = Modifier
                                .size(24.dp)
                        )
                    }
                )
            }
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomEnd),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(files) { _, file ->
                    FileItem(identityFile = file)
                }
            }
        }
    }
}