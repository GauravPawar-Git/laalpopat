package com.example.laalpopat.newui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.laalpopat.ZoomableHomeworkImage
import com.example.laalpopat.dto.Mark

@Composable
fun ReviewImageViewer(
    imageUrl: String,
    marks: List<Mark>,
    onMarkClicked: (Int) -> Unit
) {

    Card (
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
            .padding(12.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {

        Box {

            ZoomableHomeworkImage(
                imageUrl = imageUrl,
                marks = marks,
                onClose = {},
                onMarkClicked = onMarkClicked
            )

            Text(
                text = "Pinch to zoom",
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(8.dp),
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 12.sp
            )
        }
    }
}
