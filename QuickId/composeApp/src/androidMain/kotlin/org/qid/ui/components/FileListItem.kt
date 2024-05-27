package org.qid.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.qid.core.models.IdentityFile

@Preview
@Composable
fun FileListItem(
    identityFile: IdentityFile = IdentityFile.create("dfa6ff0c-b3cd-4f08-a86c-e018283165ed", "myFile.docx"),
    onClick: (identityFile: IdentityFile) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .height(68.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .clickable {
                onClick(identityFile)
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .size(68.dp)
            ) {
                Icon(
                    painter = fileIconSelector(identityFile.identityFileType),
                    contentDescription = identityFile.description,
                    modifier = Modifier
                        .padding(15.dp)
                )
            }
            Column(
                modifier = Modifier
                    .height(68.dp)
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        identityFile.name,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(top = 15.dp, start = 1.dp)
                    )
                    Text(
                        identityFile.identityFileType.toString(),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(bottom = 15.dp, start = 1.dp)
                    )
                    HorizontalDivider(
                        thickness = 1.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(alignment = Alignment.BottomEnd)
                    )
                }
            }
        }


    }
}

