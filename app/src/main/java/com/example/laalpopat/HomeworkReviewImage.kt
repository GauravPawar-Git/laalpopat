package com.example.laalpopat

import android.graphics.Paint
import android.graphics.Typeface
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.example.laalpopat.dto.Mark
import com.example.laalpopat.newui.HomeworkReviewDialog
import com.example.laalpopat.ui.theme.ErrorMark
import kotlinx.coroutines.launch

@Composable
fun HomeworkReviewImage(
    imageUrl: String,
    marks: List<Mark>
) {

    var showPreview by remember { mutableStateOf(false) }


    Box {

        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    showPreview = true
                }
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Fit,
        )

        val animatedRadius by animateFloatAsState(
            targetValue = 16f,
            animationSpec = tween (600)
        )

        Canvas(modifier = Modifier.matchParentSize()) {

            marks.forEachIndexed { index, mark ->

                val cx = size.width * mark.x
                val cy = size.height * mark.y

                drawCircle(
                    color = Color.Red.copy(0.25f),
                    radius = 55f,
                    center = Offset(cx, cy),
                    style = Stroke(width = 6f)
                )

                drawCircle(
                    color = Color.Red,
                    radius = 44f,
                    center = Offset(cx, cy),
                    style = Stroke(width = 6f)
                )

                drawContext.canvas.nativeCanvas.drawText(
                    "${index + 1}",
                    cx,
                    cy + 15,
                    Paint().apply {
                        color = android.graphics.Color.RED
                        textSize = 44f
                        textAlign = Paint.Align.CENTER
                        typeface = Typeface.DEFAULT_BOLD
                    }
                )
            }
        }

        Row (
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .background(
                    Color.Black.copy(alpha = 0.6f),
                    RoundedCornerShape(topStart = 8.dp)
                )
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                Icons.Default.Menu,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )

            Spacer(Modifier.width(4.dp))

            Text(
                "Review",
                color = Color.White,
                fontSize = 12.sp
            )
        }
    }

    if (showPreview){
        Dialog(
            onDismissRequest = { showPreview= false },
            properties = DialogProperties(usePlatformDefaultWidth = false),
        ) {

            HomeworkReviewDialog(
                imageUrl = imageUrl,
                marks = marks,
                onClose = { showPreview = false }
            )

        }
    }
}