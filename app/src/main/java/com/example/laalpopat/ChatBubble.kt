package com.example.laalpopat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.laalpopat.dto.ChatMessage

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import com.example.laalpopat.newui.HomeworkReviewCard
import com.example.laalpopat.ui.theme.CardColor
import com.example.laalpopat.ui.theme.Highlight

@Composable
fun ChatBubble(message: ChatMessage) {

    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
    }

    val isUser = when (message) {
        is ChatMessage.UserText -> true
        is ChatMessage.UserImage -> true
        is ChatMessage.AIText -> false
        is ChatMessage.AIHomeworkReview -> true
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement =
            if (isUser) Arrangement.End else Arrangement.Start
    ) {

        AnimatedVisibility(
            visible = visible,
            enter = fadeIn() + slideInVertically { it / 2 }
        ) {

            Column(
                modifier = Modifier
                    .padding(4.dp)
            ) {

                when (message) {

                    is ChatMessage.UserText -> {

                        MarkdownText(
                            text = message.text,
                            color = Color.Red
                        )
                    }

                    is ChatMessage.AIText -> {

                        MarkdownText(
                            text = message.text,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    is ChatMessage.UserImage -> {

                        AsyncImage(
                            model = message.imageUri,
                            contentDescription = null,
                            modifier = Modifier
                                .height(220.dp)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "Homework submitted",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }

                    // ⭐ THIS IS THE NEW BLOCK
                    is ChatMessage.AIHomeworkReview -> {

                        HomeworkReviewCard(message = message)

//                        Spacer(modifier = Modifier.height(12.dp))

                        // ⭐ THIS IS WHERE YOUR COLUMN GOES
//                        Column {
//
//                            Text(
//                                if (message.review.overall_result == "correct")
//                                    "✅ Correct!"
//                                else
//                                    "❌ Incorrect",
//                                fontWeight = FontWeight.Bold
//                            )
//
//                            message.review.marks.mapIndexed {index, mark ->
//
//                                mark.copy(id = index)
//
//                                Text("${index+1}. ${mark.comment}")
//                                Text("Correct answer: ${mark.correction}")
//                            }
//
//                            Spacer(modifier = Modifier.height(8.dp))
//
//                            message.review.explanation?.let { Text(it) }
//
//                            Spacer(modifier = Modifier.height(6.dp))
//
//                            message.review.encouragement?.let {
//                                Text(
//                                    it,
//                                    style = MaterialTheme.typography.labelMedium
//                                )
//                            }
//                        }
                    }
                }
            }
        }
    }
}