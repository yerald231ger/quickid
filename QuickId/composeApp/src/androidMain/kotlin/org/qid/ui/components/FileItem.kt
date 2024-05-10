package org.qid.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import core.models.IdentityFile
import org.qid.R

@Preview
@Composable
fun FileItem(
    identityFile: IdentityFile = IdentityFile(stringResource(R.string.driver_license)),
    shape: Shape = MaterialTheme.shapes.small,
) {
    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        ),
        shape = shape,
        modifier = Modifier
            .width(80.dp)
            .height(116.dp)
    ) {
        Icon(
            painter = fileIconSelector(identityFile.identityFileType),
            contentDescription = identityFile.description,
            modifier = Modifier
                .padding(top = 8.dp)
                .size(48.dp)
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


