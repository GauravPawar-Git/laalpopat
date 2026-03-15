package com.example.laalpopat

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.rememberAsyncImagePainter
import com.example.laalpopat.dto.Mark
import com.example.laalpopat.ui.theme.ErrorMark
import com.github.panpf.zoomimage.ZoomImage

@Composable
fun FullScreenImagePreview(
    imageUrl: String,
    marks: List<Mark>,
    onClose: () -> Unit
) {

    Dialog(
        onDismissRequest = onClose,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .clipToBounds()
        ) {

            ZoomImage(
                painter = rememberAsyncImagePainter(model = imageUrl),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )

            Canvas(
                modifier = Modifier.matchParentSize()
            ) {

                marks.forEachIndexed { index, mark ->

                    val cx = size.width * mark.x
                    val cy = size.height * mark.y

                    drawCircle(
                        color = Color.Red.copy(0.2f),
                        radius = 55f,
                        center = Offset(cx, cy),
                        style = Stroke(width = 7f)
                    )

                    drawCircle(
                        color = Color.Red,
                        radius = 40f,
                        center = Offset(cx, cy),
                        style = Stroke(width = 7f)
                    )

                    drawContext.canvas.nativeCanvas.drawText(
                        "${index + 1}",
                        cx,
                        cy + 15,
                        android.graphics.Paint().apply {
                            color = android.graphics.Color.RED
                            textSize = 55f
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
