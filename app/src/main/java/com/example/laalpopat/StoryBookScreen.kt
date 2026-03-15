package com.example.laalpopat

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.example.laalpopat.dto.Story
import com.example.laalpopat.service.ImageService
import com.example.laalpopat.ui.theme.CardColor
import com.example.laalpopat.ui.theme.Highlight

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StoryBookScreen(story: Story) {

    val pagerState = rememberPagerState(
        pageCount = { story.pages.size }
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Text(
            text = story.title,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)   // FIXED
        ) { page ->

            val storyPage = story.pages[page]

            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .navigationBarsPadding(),
                colors = CardDefaults.cardColors(
                    containerColor =  Highlight
                ),
                elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(24.dp)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFFFFBF2))
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {

                    Column {

                        SubcomposeAsyncImage(
                            model = storyPage.imageUrl,   // FIXED
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(260.dp)
                                .clip(RoundedCornerShape(16.dp)),
                            contentScale = ContentScale.Crop,

                            loading = {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            },

                            error = {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("Image failed to load")
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = storyPage.text,
                            fontSize = 18.sp,
                            lineHeight = 26.sp
                        )
                    }

                    Text(
                        text = "${page + 1} / ${story.pages.size}",
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        fontSize = 14.sp,
                        color = Color.DarkGray
                    )
                }
            }
        }

        PageIndicator(
            pagerState = pagerState,
            pageCount = story.pages.size
        )
    }
}