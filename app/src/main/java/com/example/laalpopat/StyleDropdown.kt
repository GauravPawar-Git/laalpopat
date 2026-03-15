package com.example.laalpopat

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun StyleDropdown(
    styles: List<String>,
    selectedStyle: String,
    onStyleSelected: (String) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }

    Box() {

        OutlinedButton (
            onClick = { expanded = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(selectedStyle)
        }

        DropdownMenu (
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {

            styles.forEach { style ->

                DropdownMenuItem(
                    text = { Text(style) },
                    onClick = {
                        onStyleSelected(style)
                        expanded = false
                    }
                )
            }
        }
    }
}