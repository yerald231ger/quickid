package org.qid.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.qid.core.models.IdentityFile

@Preview
@Composable
fun FileItem(
    identityFile: IdentityFile = IdentityFile.create(
        "dfa6ff0c-b3cd-4f08-a86c-e018283165ed",
        "myFile.docx"
    ),
    shape: Shape = MaterialTheme.shapes.small,
    isSelected: Boolean = true,
    onClickAction: (id: IdentityFile) -> Unit = {}
) {
    Card(
        onClick = { onClickAction(identityFile) },
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.secondaryContainer,
            contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSecondaryContainer
        ),
        modifier = Modifier
            .size(width = 240.dp, height = 120.dp)
    ) {
        Icon(
            painter = fileIconSelector(identityFile.identityFileType),
            contentDescription = identityFile.description,
            modifier = Modifier
                .padding(top = 8.dp)
                .size(68.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = identityFile.identityFileType.toString(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 4.dp, top = 4.dp),
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            overflow = TextOverflow.Ellipsis,
            text = identityFile.name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 4.dp),
            style = MaterialTheme.typography.bodySmall
        )
    }
}


