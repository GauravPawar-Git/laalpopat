package com.example.laalpopat.dto

sealed class ChatMessage {

    data class UserText(
        val text: String
    ) : ChatMessage()

    data class UserImage(
        val imageUri: String
    ) : ChatMessage()

    data class AIText(
        val text: String
    ) : ChatMessage()

    data class AIHomeworkReview(
        val imageUri: String,
        val review: HomeworkReview
    ) : ChatMessage()

}