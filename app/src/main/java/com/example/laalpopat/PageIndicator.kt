package com.example.laalpopat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PageIndicator(pagerState: PagerState, pageCount: Int) {

    Row (
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {

        repeat(pageCount) { index ->

            val color =
                if (pagerState.currentPage == index)
                    Color.Black
                else
                    Color.LightGray

            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(8.dp)
                    .background(color, CircleShape)
            )
        }
    }
}