package com.example.laalpopat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.laalpopat.dto.Mark
import kotlinx.coroutines.launch

@Composable
fun HomeworkReviewScreen(
    imageUrl: String,
    marks: List<Mark>,
    onClose: () -> Unit
) {

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    var selectedMark by remember { mutableStateOf<Int?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            ZoomableHomeworkImage(
                imageUrl = imageUrl,
                marks = marks,
                onClose = onClose,
                onMarkClicked = { index ->

                    selectedMark = index

                    coroutineScope.launch {
                        listState.animateScrollToItem(index)
                    }
                }
            )
        }

        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(1f)
                .background(Color.White)
        ) {

            itemsIndexed(marks) { index, mark ->

                val isSelected = selectedMark == index

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            if (isSelected) Color(0xFFFFF3CD)
                            else Color.Transparent
                        )
                        .padding(12.dp)
                ) {

                    Text(
                        "${index + 1}. ${mark.comment}",
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        "Correct answer: ${mark.correction}"
                    )
                }
            }
        }
    }
}
