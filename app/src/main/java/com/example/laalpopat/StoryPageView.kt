package com.example.laalpopat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.laalpopat.dto.StoryPage
import com.example.laalpopat.service.ImageService

@Composable
fun StoryPageView(page: StoryPage, pageOffset: Int) {

    val imageUrl = ImageService.generateImage(page.imagePrompt)

    Card(
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
    ) {

        Column {

            ParallaxImage(imageUrl, pageOffset)

            Text(
                page.text,
                modifier = Modifier.padding(20.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}