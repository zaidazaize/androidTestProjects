package com.example.basicstatecodelab.models

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.basicstatecodelab.StatefulWellnessTaskItem

private fun getWellnessTask() = List(30) { i -> WellnessTask(i, "Task $i") }

@Composable
fun WellnessTaskList(
    modifier: Modifier, list: List<WellnessTask> = remember {
        getWellnessTask()
    }
) {

    LazyColumn(modifier = modifier) {
        items(list) { task ->
            StatefulWellnessTaskItem(taskName = task.label)
        }
    }
}
