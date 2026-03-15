package com.example.laalpopat.web

import com.example.laalpopat.dto.StoryRequest
import com.example.laalpopat.dto.StoryResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface StoryApi {

    @POST("generateStory")
    suspend fun generateStory(
        @Body request: StoryRequest
    ): StoryResponse
}