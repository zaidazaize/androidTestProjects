package com.example.basicstatecodelab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.basicstatecodelab.models.WellnessTask
import com.example.basicstatecodelab.models.WellnessTaskList

@Composable
fun WellnessScreen(modifier: Modifier = Modifier) {
    var count by rememberSaveable {
        mutableStateOf(0)
    }
    Column {
        StateFulWaterCounter(Modifier.padding(8.dp))
        val list = remember {
            getWellnessTask().toMutableStateList()

        }
        WellnessTaskList(modifier = Modifier.padding(8.dp),list = list,onCloseTask = {task->list.remove(task)})
    }
}
private fun getWellnessTask() = List(30) { i -> WellnessTask(i, "Task $i") }
