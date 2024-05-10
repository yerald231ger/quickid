package org.qid.ui.screen

import androidx.appcompat.widget.DialogTitle
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
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
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AddIdentityFileDialog {
            showDialog = false
        }
    }

    Box(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface).fillMaxSize()
    ) {
        Column {
            Button(onClick = {
                showDialog = true
            }) {
                Text("Add Identity File")
            }
            HelloSection()
            QuickIdentityFilesSection(fileRepository.getTopFiles())
            RecentIdentityFileSection(fileRepository.getTopFiles())
        }
    }
}

@Preview
@Composable
fun AddIdentityFileDialog(onDismissRequest: () -> Unit = {}) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier.fillMaxWidth().height(400.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
            ) {
                Row {
                    Text(
                        "Add Identity File",
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
                Row {
                    Text(
                        "Select the source of the identity file to import",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(top = 16.dp, bottom = 24.dp)
                    )
                }
                Row {
                    Column {
                        val sources = listOf("Scan", "Gallery", "File")
                        sources.forEach { source ->
                            Row(
                                modifier = Modifier.height(57.dp).fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier.size(40.dp).clip(RoundedCornerShape(40.dp))
                                        .background(MaterialTheme.colorScheme.secondaryContainer),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Phone,
                                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                                        contentDescription = "File",
                                    )
                                }
                                Text(
                                    source,
                                    style = MaterialTheme.typography.bodyLarge,
                                    modifier = Modifier.padding(start = 16.dp)
                                )
                            }
                            HorizontalDivider()
                        }
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxSize().padding(top = 16.dp)
                ) {
                    Button(
                        onClick = {
                            onDismissRequest()
                        },
                        modifier = Modifier
                            .height(40.dp)
                            .width(100.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = MaterialTheme.colorScheme.onSecondary,
                            containerColor = MaterialTheme.colorScheme.secondary
                        )
                    ) {
                        Text(
                            "Cancel",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }
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
fun QuickIdentityFilesSection(files: List<IdentityFile>, onClickEdit: () -> Unit = {}) {
    Row {
        TitleContainer(
            title = stringResource(R.string.recent_identity_file), label = "+ Add"
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