package com.example.laalpopat.dto

data class StoryRequest(
    val prompt: String,
    val language: String,
    val style: String = "Adventure"
)