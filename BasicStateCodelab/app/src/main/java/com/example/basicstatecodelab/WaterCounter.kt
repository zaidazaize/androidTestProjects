package com.example.basicstatecodelab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        var count by remember {
            mutableStateOf(0)
        }
        if (count > 0) {
            var showTask by remember {
                mutableStateOf(true)
            }
            if (showTask) {
                WellnessTaskItem(taskName = "Have you taken 15 minutes to walk today?",
                    onClose = { showTask = false })
            }
            Text(
                text = "You have ${count} glasses",
            )
        }
        Row(modifier = Modifier.padding(top = 8.dp)) {
            Button(
                onClick = { count++ }, enabled = count < 10
            ) {
                Text(text = "Add One")
            }
            Button(
                onClick = { count = 0 },
                modifier = Modifier.padding(start = 16.dp),
            ) {
                Text(text = "Reset")
            }
        }
    }

}
