package com.example.laalpopat

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.laalpopat.dto.Mark
import com.example.laalpopat.ui.theme.ErrorMark
import kotlin.math.hypot

@Composable
fun ZoomableHomeworkImage(
    imageUrl: String,
    marks: List<Mark>,
    onClose: () -> Unit,
    onMarkClicked: (Int) -> Unit
) {

    var scale by remember { mutableStateOf(1f) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    val transformableState = rememberTransformableState { zoomChange, panChange, _ ->

        scale = (scale * zoomChange).coerceIn(1f, 4f)
        offsetX += panChange.x
        offsetY += panChange.y
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .clipToBounds()
            .transformable(state = transformableState)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offsetX,
                    translationY = offsetY
                )
        ) {

            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Fit
            )

            Canvas (modifier = Modifier
                .matchParentSize()
                .pointerInput(scale, offsetX, offsetY) {

                    detectTapGestures { tapOffset ->
                        // Convert tap position to original image coordinates
                        val adjustedX = (tapOffset.x - offsetX) / scale
                        val adjustedY = (tapOffset.y - offsetY) / scale

                        marks.forEachIndexed { index, mark ->

                            val cx = size.width * mark.x
                            val cy = size.height * mark.y

                            val distance =
                                hypot(adjustedX - cx, adjustedY - cy)

                            if (distance < 120f) {
                                onMarkClicked(index)
                            }
                        }
                    }

                }) {

                marks.forEachIndexed { index, mark ->

                    val x = size.width * mark.x
                    val y = size.height * mark.y

                    drawCircle(
                        color = Color.Red.copy(0.2f),
                        radius = 55f,
                        center = Offset(x, y),
                        style = Stroke(width = 6f)
                    )

                    drawCircle(
                        color = Color.Red,
                        radius = 44f,
                        center = Offset(x, y),
                        style = Stroke(width = 6f)
                    )

                    drawContext.canvas.nativeCanvas.drawText(
                        "${index + 1}",
                        x,
                        y + 15,
                        android.graphics.Paint().apply {
                            color = android.graphics.Color.RED
                            textSize = 50f
                            textAlign = android.graphics.Paint.Align.CENTER
                            typeface = android.graphics.Typeface.DEFAULT_BOLD
                        }
                    )
                }
            }

            IconButton(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp),
                onClick = onClose
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}
