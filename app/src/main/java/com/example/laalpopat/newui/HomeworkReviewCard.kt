package com.example.laalpopat.newui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.laalpopat.HomeworkReviewImage
import com.example.laalpopat.dto.ChatMessage

@Composable
fun HomeworkReviewCard(message: ChatMessage.AIHomeworkReview) {

    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {

        Column (
            modifier = Modifier.padding(12.dp)
        ) {

            Text(
                text = "Homework Review",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Spacer(Modifier.height(8.dp))

            HomeworkReviewImage(
                imageUrl = message.imageUri,
                marks = message.review.marks
            )

            Spacer(Modifier.height(10.dp))

            val correct = message.review.overall_result == "correct"

            Row (verticalAlignment = Alignment.CenterVertically) {

                Text(
                    if (correct) "✅ Correct"
                    else "❌ Incorrect",
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.width(8.dp))

                Text(
                    "(${message.review.marks.size} mistakes)",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(Modifier.height(8.dp))

            message.review.marks.take(2).forEachIndexed { index, mark ->

                Text(
                    "${index + 1}. ${mark.comment}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            if (message.review.marks.size > 2) {

                Text(
                    "Tap image to see all corrections",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            message.review.encouragement?.let {

                Spacer(Modifier.height(6.dp))

                Text(
                    it,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}
