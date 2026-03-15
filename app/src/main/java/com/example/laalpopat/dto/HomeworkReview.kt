package com.example.laalpopat.dto

data class HomeworkReview(
    val overall_result: String? = null,
    val marks: List<Mark> = emptyList(),
    val explanation: String? = null,
    val encouragement: String? = null
)
