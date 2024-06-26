package org.qid.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.qid.core.constants.IdentityFileType
import org.qid.core.models.IdentityFile
import org.qid.ui.icons.Description
import org.qid.viewModels.EditIdentityFileEvent
import org.qid.viewModels.EditIdentityFileViewModel

@Composable

fun EditIdentityFileDialog(
    identityFile: IdentityFile,
    viewModel: EditIdentityFileViewModel,
    onDismissRequest: () -> Unit = {},
    onEditedIdentityFile: (IdentityFile) -> Unit = {},
) {
    val state by viewModel.state.collectAsState()

    viewModel.onEvent(EditIdentityFileEvent.NameChanged(identityFile.name))
    viewModel.onEvent(EditIdentityFileEvent.DescriptionChanged(identityFile.description))

    val identityFileTypes = IdentityFileType.entries
    val importanceValues = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    var importanceSelected by remember { mutableStateOf(identityFile.importance.toString()) }
    var identityFileTypesSelected by remember { mutableStateOf(identityFile.identityFileType.toString()) }
    var expandedImportance by remember { mutableStateOf(false) }
    var expandedType by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = {
        onDismissRequest()
    }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Description,
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp, 24.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    "Edit Identity File",
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    identityFile.name,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.bodyMedium
                )
                OutlinedTextField(
                    state.name,
                    onValueChange = {
                        viewModel.onEvent(EditIdentityFileEvent.NameChanged(it))
                    },
                    label = {
                        Text("File name")
                    },
                    isError = state.nameError != null,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth(),
                    singleLine = true,
                    supportingText = {
                        if (!state.nameError.isNullOrEmpty())
                            Text(state.nameError!!)
                    }
                )
                OutlinedTextField(
                    state.description,
                    onValueChange = {
                        viewModel.onEvent(EditIdentityFileEvent.DescriptionChanged(it))
                    },
                    label = {
                        Text("Description")
                    },
                    isError = state.descriptionError != null,
                    modifier = Modifier
                        .fillMaxWidth(),
                    singleLine = true,
                    supportingText = {
                        if (!state.descriptionError.isNullOrEmpty())
                            Text(state.descriptionError!!)
                    }
                )

                OutlinedTextField(
                    importanceSelected,
                    onValueChange = {
                    },
                    label = { Text("Importance") },
                    readOnly = true,
                    trailingIcon = {
                        Box {
                            IconButton(onClick = { expandedImportance = true }) {
                                Icon(
                                    Icons.Default.ArrowDropDown,
                                    contentDescription = "Localized description"
                                )
                            }
                            DropdownMenu(
                                expanded = expandedImportance,
                                onDismissRequest = { expandedImportance = false }
                            ) {
                                importanceValues.forEach {
                                    DropdownMenuItem(
                                        text = { Text(it.toString()) },
                                        onClick = {
                                            importanceSelected = it.toString()
                                            expandedImportance = false
                                        })
                                }
                            }
                        }
                    },
                    isError = state.importanceError != null,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                OutlinedTextField(
                    value = identityFileTypesSelected,
                    onValueChange = {

                    },
                    label = { Text("Type") },
                    readOnly = true,
                    trailingIcon = {
                        Box {
                            IconButton(onClick = { expandedType = true }) {
                                Icon(
                                    Icons.Default.ArrowDropDown,
                                    contentDescription = "Localized description"
                                )
                            }
                            DropdownMenu(
                                expanded = expandedType,
                                onDismissRequest = { expandedType = false }
                            ) {
                                identityFileTypes.forEach {
                                    DropdownMenuItem(
                                        text = { Text(it.toString()) },
                                        onClick = {
                                            identityFileTypesSelected = it.toString()
                                            expandedType = false
                                        })
                                }
                            }
                        }
                    },
                    isError = state.typeError != null,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(onClick = {
                        onDismissRequest()
                    }
                    ) {
                        Text(text = "Cancel")
                    }
                    Button(
                        onClick = {
                            identityFile.name = state.name
                            identityFile.description = state.description
                            identityFile.identityFileType = IdentityFileType.fromString(identityFileTypesSelected)
                            identityFile.importance = importanceSelected.toInt()
                            onEditedIdentityFile(identityFile)
                        },
                        enabled = state.isModelValid,
                        modifier = Modifier.padding(start = 16.dp)
                    ) {
                        Text(text = "Save")
                    }
                }
            }
        }
    }
}