package com.example.laalpopat

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.jeziellago.compose.markdowntext.MarkdownText

@Composable
fun MarkdownText(text: String, color: Color) {

    MarkdownText(
        markdown = text,
        color = color,
        modifier = Modifier.padding(4.dp)
    )
}