package com.example.laalpopat

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ParallaxImage(imageUrl: String, pageOffset: Int) {

    val offset = pageOffset * 40

    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp)
            .graphicsLayer {
                translationX = offset.toFloat()
            }
    )
}