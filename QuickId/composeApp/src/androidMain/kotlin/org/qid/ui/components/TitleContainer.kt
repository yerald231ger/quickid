package org.qid.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun TitleContainer(
    title: String = "Title",
    label: String = "label",
    onClickLabel: () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    val context = LocalContext.current;
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Column {
            Row {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                    )
                    IconButton(
                        onClick = onClickLabel,
                        content = {
                            Text(
                                text = label,
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        },
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                    )

                }
            }
            Row(
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                content()
            }
        }
    }
}