package com.example.laalpopat.newui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.laalpopat.dto.Mark
import com.example.laalpopat.ui.theme.Highlight

@Composable
fun ReviewMistakeList(
    marks: List<Mark>,
    listState: LazyListState,
    selectedMark: Int?
) {

    Column (
        modifier = Modifier.fillMaxSize()
    ) {

        Text(
            text = "Mistakes (${marks.size})",
            modifier = Modifier.padding(start = 16.dp, top = 8.dp),
            fontWeight = FontWeight.SemiBold
        )

        LazyColumn (
            state = listState,
            modifier = Modifier.fillMaxSize()
        ) {

            itemsIndexed(marks) { index, mark ->

                val isSelected = selectedMark == index

                Card (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    colors = CardDefaults.cardColors(
                        containerColor =
                            if (isSelected) Highlight
                            else Color.White
                    ),
                    elevation = CardDefaults.cardElevation(3.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(14.dp)
                    ) {

                        Text(
                            text = "Mistake ${index + 1}",
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(Modifier.height(4.dp))

                        Text(
                            text = mark.comment,
                            color = Color.DarkGray
                        )

                        Spacer(Modifier.height(6.dp))

                        Text(
                            text = "Correct: ${mark.correction}",
                            color = Color(0xFF1976D2),
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}
