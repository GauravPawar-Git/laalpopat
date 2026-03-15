package com.example.laalpopat.service

object ImageService {

    fun generateImage(prompt: String): String {

        val imagePrompt = "Children story illustration: $prompt"

        val url =
            "https://image.pollinations.ai/prompt/${imagePrompt.replace(" ","%20")}"

        return url
    }
}