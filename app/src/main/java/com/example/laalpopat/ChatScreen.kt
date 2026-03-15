package com.example.laalpopat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.laalpopat.viewmodel.ChatViewModel
import com.example.laalpopat.viewmodel.StoryViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.laalpopat.newui.HomeworkReviewLoader

@Composable
fun ChatScreen(viewModel: ChatViewModel = viewModel()) {

    var text by remember { mutableStateOf("") }
    val listState = rememberLazyListState()

    Column(
        modifier = Modifier.fillMaxSize().statusBarsPadding()
    ) {

        val story = viewModel.story

        story?.let {

            StoryBookScreen(story = it)
        }

        LazyColumn (
            modifier = Modifier.weight(1f),
            reverseLayout = true
        ) {

            items(viewModel.messages.reversed()) { message ->
                ChatBubble(message)
            }
        }

        LaunchedEffect(viewModel.messages.size) {
            listState.animateScrollToItem(viewModel.messages.size - 1)
        }
        if (viewModel.isLoading) {
            HomeworkReviewLoader()
        }
        ChatInputBar(
            text = text,
            onTextChange = { text = it },
            onSend = {
                if (text.isNotBlank()) {
                    viewModel.generateStory(text)
                    text = ""
                }
            },
            onVoiceClick = {
                // voice input later
            },
            onLanguageClick = {
                // open language picker
            }
        )
    }
}