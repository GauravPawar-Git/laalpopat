package com.example.laalpopat.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.laalpopat.GeminiService
import com.example.laalpopat.dto.StoryRequest
import com.example.laalpopat.dto.StoryResponse
import com.example.laalpopat.web.RetrofitClient
import kotlinx.coroutines.launch

class StoryViewModel : ViewModel() {

    var storyResponse by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set

    fun generateStory(prompt: String, language: String) {

        viewModelScope.launch {

            isLoading = true

            try {

                val result = GeminiService.generateStory(
                    prompt = prompt,
                    language = language
                )

                storyResponse = result

            } catch (e: Exception) {
                e.printStackTrace()
            }

            isLoading = false
        }
    }
}