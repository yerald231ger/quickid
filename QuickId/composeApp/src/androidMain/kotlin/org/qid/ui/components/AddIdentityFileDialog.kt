import android.app.Activity
import android.app.Activity.RESULT_OK
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.OpenDocument
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.activity.result.contract.ActivityResultContracts.StartIntentSenderForResult
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.RESULT_FORMAT_JPEG
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.RESULT_FORMAT_PDF
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.SCANNER_MODE_FULL
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult
import core.models.IdentityFile
import org.qid.ui.icons.DocumentScanner
import org.qid.ui.icons.PhotoLibrary
import org.qid.ui.icons.Storage
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Preview
@Composable
fun AddIdentityFileDialog(onDismissRequest: () -> Unit = {}) {

    val context = LocalContext.current
    val options =
        GmsDocumentScannerOptions.Builder()
            .setPageLimit(3)
            .setScannerMode(SCANNER_MODE_FULL)
            .setGalleryImportAllowed(true)
            .setResultFormats(RESULT_FORMAT_JPEG, RESULT_FORMAT_PDF)
            .build()

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        PickVisualMedia()
    ) { uri ->

        val inputStream = uri?.let { context.contentResolver.openInputStream(it) }
        val dateNow = LocalDateTime.now()

        @Suppress("SpellCheckingInspection")
        val dateNowFormatted = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(dateNow)
        val file = File(context.filesDir, IdentityFile.createNameForImageFile(dateNowFormatted))
        val outputStream = file.outputStream()
        inputStream?.copyTo(outputStream)
        inputStream?.close()
        outputStream.close()
    }

    var fileUri by remember { mutableStateOf<Uri?>(null) }
    val singleFilePickerLauncher = rememberLauncherForActivityResult(
        OpenDocument()
    ) { fileUri = it }

    val scanner = GmsDocumentScanning.getClient(options)
    var scannedDocumentUri by remember { mutableStateOf<List<Uri>>(emptyList()) }
    val scanDocumentLauncher =
        rememberLauncherForActivityResult(StartIntentSenderForResult()) { activityResult ->
            if (activityResult.resultCode == RESULT_OK) {
                val result = GmsDocumentScanningResult.fromActivityResultIntent(activityResult.data)
                scannedDocumentUri = result?.pages?.map { it.imageUri } ?: emptyList()
                result?.pdf?.let { pdf ->
                    val pdfUri = pdf.uri
                    val pageCount = pdf.pageCount
                }
            }
        }

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
                        val sources = listOf(
                            "Scan" to Icons.Default.DocumentScanner,
                            "Photo Gallery" to Icons.Default.PhotoLibrary,
                            "File Manager" to Icons.Default.Storage
                        )
                        sources.forEach { source ->
                            Row(
                                modifier = Modifier.height(57.dp).fillMaxWidth().clickable {
                                    when (source.first) {
                                        "Scan" -> {
                                            scanner.getStartScanIntent(context as Activity)
                                                .addOnSuccessListener {
                                                    scanDocumentLauncher.launch(
                                                        IntentSenderRequest.Builder(
                                                            it
                                                        ).build()
                                                    )
                                                }
                                                .addOnFailureListener {
                                                    Toast.makeText(
                                                        context,
                                                        "Failed to start scan",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                }
                                        }

                                        "Photo Gallery" -> {
                                            singlePhotoPickerLauncher.launch(
                                                PickVisualMediaRequest()
                                            )
                                        }

                                        "File Manager" -> {
                                            singleFilePickerLauncher.launch(
                                                arrayOf(
                                                    "application/pdf",
                                                    "application/msword",
                                                    "text/plain",
                                                )
                                            )
                                        }
                                    }
                                }, verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier.size(40.dp).clip(RoundedCornerShape(40.dp))
                                        .background(MaterialTheme.colorScheme.secondaryContainer),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = source.second,
                                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                                        contentDescription = "File",
                                    )
                                }
                                Text(
                                    source.first,
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
                        modifier = Modifier.height(40.dp).width(100.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = MaterialTheme.colorScheme.onSecondary,
                            containerColor = MaterialTheme.colorScheme.secondary
                        )
                    ) {
                        Text(
                            "Cancel", style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }
        }
    }
}
