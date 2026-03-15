package com.example.laalpopat

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.laalpopat.viewmodel.ChatViewModel
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatInputBar(
    text: String,
    onTextChange: (String) -> Unit,
    onSend: () -> Unit,
    onVoiceClick: () -> Unit,
    onLanguageClick: () -> Unit
) {

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var viewModel: ChatViewModel = viewModel()

    var showPicker by remember { mutableStateOf(false) }

    val context = LocalContext.current

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            viewModel.reviewHomework(context, it)
        }
    }

    val photoUri = remember { mutableStateOf<Uri?>(null) }

    val cameraLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.TakePicture()
        ) { success ->

            if (success) {
                photoUri.value?.let { uri ->
                    viewModel.reviewHomework(context, uri)
                }
            }
        }

    val cameraPermissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granted ->

            if (granted) {

                val uri = createImageUri(context)
                photoUri.value = uri
                uri?.let { cameraLauncher.launch(it) }

            } else {

                Toast.makeText(
                    context,
                    "Camera permission required",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


    Surface(
        tonalElevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        color = Color.White
    ) {

        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(onClick = {
//                imagePicker.launch("image/*")
                showPicker = true
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_gallery_photo),
                    contentDescription = "Upload homework",
                    tint = Color.Black
                )
            }

            OutlinedTextField(
                value = text,
                onValueChange = onTextChange,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp),
                placeholder = { Text("Ask or upload homework...") },
                shape = RoundedCornerShape(28.dp),
                maxLines = 4
            )

            AnimatedContent(targetState = text.isBlank()) { blank ->

/*                if (blank) {

                    IconButton(onClick = onVoiceClick) {
                        Icon(
                            Icons.Default.Phone,
                            contentDescription = "Voice"
                        )
                    }

                } else {*/

                IconButton(onClick = onSend) {
                    Icon(
                        Icons.Default.Send,
                        contentDescription = "Send",
                        tint = Color.Black
                    )
                }
//                }
            }
        }
    }


    fun createImageUri(context: Context): Uri {

        val file = File(
            context.cacheDir,
            "homework_${System.currentTimeMillis()}.jpg"
        )

        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )
    }

    if (showPicker) {

        ModalBottomSheet (
            onDismissRequest = { showPicker = false }
        ) {

            Column {

                ListItem(
                    headlineContent = { Text("Take Photo") },
                    leadingContent = {
                        Icon(painter = painterResource(R.drawable.ic_camera), null)
                    },
                    modifier = Modifier.clickable {

                        showPicker = false

                        val permission =
                            ContextCompat.checkSelfPermission(
                                context,
                                Manifest.permission.CAMERA
                            )

                        if (permission == PackageManager.PERMISSION_GRANTED) {

                            val uri = createImageUri(context)
                            photoUri.value = uri

                            cameraLauncher.launch(uri)
                        } else{
                            cameraPermissionLauncher.launch(
                                Manifest.permission.CAMERA
                            )
                        }
                    }
                )

                ListItem(
                    headlineContent = { Text("Choose from Gallery") },
                    leadingContent = {
                        Icon(painter = painterResource(R.drawable.ic_gallery_photo), null)
                    },
                    modifier = Modifier.clickable {

                        showPicker = false
                        imagePicker.launch("image/*")
                    }
                )
            }
        }
    }


}

fun createImageUri(context: Context): Uri? {
    val file = File(
        context.cacheDir,
        "homework_${System.currentTimeMillis()}.jpg"
    )

    return FileProvider.getUriForFile(
        context,
        "${context.packageName}.provider",
        file
    )
}
