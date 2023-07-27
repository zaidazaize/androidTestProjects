package com.example.basicstatecodelab

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.basicstatecodelab.models.WellnessTask

class WellnessViewModal : ViewModel() {
    private val _task = getWellnessTask().toMutableStateList()
    val task: List<WellnessTask>
        get() = _task

    fun removeTask(task: WellnessTask) {
        _task.remove(task)
    }

    fun changeTaskChecked(item: WellnessTask, checked: Boolean) {
        _task.find { it.id == item.id }?.let { task ->
            task.checked = checked
        }
    }

    private fun getWellnessTask() = List(30) { i -> WellnessTask(i, "Task $i") }
}