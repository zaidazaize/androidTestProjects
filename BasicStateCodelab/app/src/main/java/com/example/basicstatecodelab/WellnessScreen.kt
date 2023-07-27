package com.example.basicstatecodelab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.basicstatecodelab.models.WellnessTaskList

@Composable
fun WellnessScreen(
    modifier: Modifier = Modifier,
    wellnessViewModal: WellnessViewModal = viewModel()
) {
    Column {
        StateFulWaterCounter(Modifier.padding(8.dp))

        WellnessTaskList(
            modifier = Modifier.padding(8.dp),
            list = wellnessViewModal.task,
            onCheckedTask = { task, checked -> wellnessViewModal.changeTaskChecked(task, checked) },
            onCloseTask = { task -> wellnessViewModal.removeTask(task) })
    }
}
