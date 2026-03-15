package com.example.laalpopat.viewmodel

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.laalpopat.GeminiService
import com.example.laalpopat.dto.ChatMessage
import com.example.laalpopat.dto.HomeworkReview
import com.example.laalpopat.dto.Story
import com.example.laalpopat.service.ImageService
import com.google.gson.Gson
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {

    var messages = mutableStateListOf<ChatMessage>()
        private set

    var isLoading by mutableStateOf(false)
        private set

    fun sendMessage(prompt: String) {

        val imageUrl = ImageService.generateImage(prompt)
        val chatMessage = ChatMessage.UserText(prompt)
        messages.add(chatMessage)

        viewModelScope.launch {

            isLoading = true

            val response = GeminiService.generateChatResponse(
                messages = messages,
                language = "English"
            )

            messages.add(ChatMessage.AIText(response))

            isLoading = false
        }
    }

    var story by mutableStateOf<Story?>(null)

    fun generateStory(prompt: String) {

        viewModelScope.launch {

            isLoading = true

            val result = GeminiService.generateChildStory(prompt,"English")

            story = result

            isLoading = false
        }
    }

    fun reviewHomework(context: Context,imageUri: Uri) {

        messages.add(ChatMessage.UserImage(imageUri.toString()))

        viewModelScope.launch {

            isLoading = true
            val result = GeminiService.reviewHomework(context, imageUri)

            Log.i("ssssssss", result)
            val cleanJson = result
                .replace("```json", "")
                .replace("```", "")
                .trim()

            Log.i("ssssssss", cleanJson)

            isLoading = false
            messages.add(
                ChatMessage.AIHomeworkReview(
                    imageUri = imageUri.toString(),
                    review = Gson().fromJson(cleanJson, HomeworkReview::class.java)
                )
            )
        }
    }
}