package com.example.laalpopat.dto

data class StoryPage(
    val text: String,
    val imagePrompt: String,
    var imageUrl: String? = null
)