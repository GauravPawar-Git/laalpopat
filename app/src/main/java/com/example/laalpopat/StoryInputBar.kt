package com.example.laalpopat

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StoryInputBar(
    text: String,
    selectedLanguage: String,
    onTextChange: (String) -> Unit,
    onLanguageClick: () -> Unit,
    onVoiceClick: () -> Unit,
    onSendClick: () -> Unit
) {

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton (onClick = onLanguageClick) {
            Icon(
//                imageVector = Icons.Default.Language,
                imageVector = Icons.Default.Build,
                contentDescription = "Language"
            )
        }

        OutlinedTextField(
            value = text,
            onValueChange = onTextChange,
            placeholder = { Text("Enter story idea...") },
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp),
            maxLines = 3
        )

        IconButton(onClick = onVoiceClick) {
            Icon(
//                imageVector = Icons.Default.Mic,
                imageVector = Icons.Default.Call,
                contentDescription = "Voice Input"
            )
        }

        IconButton(onClick = onSendClick) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = "Send"
            )
        }
    }
}