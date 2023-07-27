package com.example.basicstatecodelab

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.withContext

@Composable
fun WellnessTaskItem(
    modifier: Modifier = Modifier,
    taskName: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClose: () -> Unit,
) {

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = taskName, modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        )
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }

}

@Composable
fun StatefulWellnessTaskItem(
    modifier: Modifier = Modifier,
    taskName: String,
) {
    var checked by rememberSaveable {
        mutableStateOf(false)
    }
   WellnessTaskItem(
        modifier = modifier,
        taskName = taskName,
        checked = checked,
        onCheckedChange = {newValue-> checked = newValue},
        onClose = {})
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WellnessTaskItemPreview() {

    StatefulWellnessTaskItem(taskName = "Drink Water")
}