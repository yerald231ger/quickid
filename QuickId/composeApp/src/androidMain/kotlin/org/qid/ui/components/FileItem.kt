package org.qid.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
    shape: Shape = MaterialTheme.shapes.medium
) {
    Box(
        modifier = Modifier
            .width(80.dp)
            .height(116.dp)
            .clip(shape)
            .border(1.dp, MaterialTheme.colorScheme.outline, shape)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
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
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                overflow = TextOverflow.Ellipsis,
                text = identityFile.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 4.dp),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodySmall
            )
        }

    }
}


