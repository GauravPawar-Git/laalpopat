package com.example.laalpopat.dto

data class StoryResponse(
    val title: String,
    val story: String,
    val scenes: List<StoryScene>
)