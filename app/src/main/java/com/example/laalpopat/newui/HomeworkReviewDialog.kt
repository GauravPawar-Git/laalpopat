package com.example.laalpopat.newui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.laalpopat.dto.Mark
import kotlinx.coroutines.launch

@Composable
fun HomeworkReviewDialog(
    imageUrl: String,
    marks: List<Mark>,
    onClose: () -> Unit
) {

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope ()
    var selectedMark by remember { mutableStateOf<Int?>(null) }

    Surface (
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF6F7FB)
    ) {

        Column {

            ReviewTopBar(onClose)

            ReviewImageViewer(
                imageUrl = imageUrl,
                marks = marks,
                onMarkClicked = { index ->

                    selectedMark = index

                    coroutineScope.launch {
                        listState.animateScrollToItem(index)
                    }
                }
            )

            ReviewMistakeList(
                marks = marks,
                listState = listState,
                selectedMark = selectedMark
            )
        }
    }
}
