package com.example.laalpopat

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import com.example.laalpopat.dto.ChatMessage
import com.example.laalpopat.dto.Story
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.google.gson.Gson

object GeminiService {

    private val model = GenerativeModel(
        modelName = "models/gemini-2.5-flash",
        apiKey = BuildConfig.GEMINI_API_KEY
    )

    suspend fun generateStory(
        prompt: String,
        language: String
    ): String {

        val fullPrompt = """
            Create a children's story.

            Language: $language
            Theme: $prompt

            Return:
            Title
            Story in paragraphs
        """.trimIndent()

        val response = model.generateContent(
            content {
                Log.i("dddddddddd", fullPrompt)
                text(fullPrompt)
            }
        )

        Log.i("dddddddddd", Gson().toJson(response))

        return response.text ?: "No story generated"
    }

    suspend fun generateChatResponse(
        messages: List<ChatMessage>,
        language: String
    ): String {

        val promptBuilder = StringBuilder()

        promptBuilder.append("You are a children's storyteller.\n")
        promptBuilder.append("Language: $language\n\n")

        messages.forEach {
//            if (it.isUser) {
//                promptBuilder.append("User: ${it.text}\n")
//            } else {
//                promptBuilder.append("AI: ${it.text}\n")
//            }
        }

        val response = model.generateContent(
            content {
                text(promptBuilder.toString())
            }
        )

        return response.text ?: "No response"
    }

    suspend fun generateChildStory(
        messages: String,
        language: String
    ): Story? {


        val fullPrompt = """
Create a children's story.

Language: $language
Theme: ${messages}

Return JSON in this format:

{
 "title": "Story title",
 "pages":[
   {
     "text":"Story page text",
     "imagePrompt":"Description for illustration"
   }
 ]
}

Create exactly 5 pages.
Keep text short and simple for kids.
""".trimIndent()


        val response = model.generateContent(
            content {
                text("$fullPrompt\nTheme: ${messages}")

            }
        )

        Log.i("Sssssssss", "108");
        response.text?.let { Log.i("Sssssssss", it) };

        val raw = response.text ?: "{}"

        val cleanedJson = raw
            .replace("```json", "")
            .replace("```", "")
            .trim()

        return Gson().fromJson(cleanedJson, Story::class.java)
    }


    suspend fun reviewHomework(context: Context, imageUri: Uri): String {
        val bitmap = uriToBitmap(context, imageUri)


        val response = model.generateContent(
            content {

                text(
                    """
You are a teacher checking a student's worksheet.

Carefully read the worksheet in the image.

Identify ONLY the student's incorrect answers.

Return JSON ONLY in this format:

{
 "marks":[
  {
   "x":0.0,
   "y":0.0,
   "comment":"Short description of what is wrong",
   "correction":"The correct word or answer"
  }
 ]
}

Rules:

• x and y must be between 0 and 1
• coordinates must point to the student's written answer
• do NOT guess answers
• read the worksheet carefully
• correction must contain only the correct answer (no extra text)

Do not include text outside JSON.
""".trimIndent()
                )

                image(bitmap)
            }
        )

        return response.text ?: ""
    }

    fun uriToBitmap(
        context: Context,
        uri: Uri
    ): Bitmap {

        val inputStream = context.contentResolver.openInputStream(uri)
        return BitmapFactory.decodeStream(inputStream)
    }
}