package com.example.taskmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanager.ui.theme.TaskManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskManagerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                }
            }
        }
    }
}

@Composable
fun ShowTaskCompletedScreen() {
    val painter = painterResource(id = R.drawable.ic_task_completed)
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painter, contentDescription = null)
        Text(
            text = "All Task Completed",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
        )
        Text(text = "Nice Work", fontSize = 16.sp)
    }
}

// Make a 4 quadrants of the screen and add four articles to it
@Composable
fun MakeQuadrant() {
    //make two rows of size of half of the screen

    Column(Modifier.fillMaxHeight(1f)) {
        Row(Modifier.weight(1f)) {
            QuadrantArticle(
                modifier = Modifier
                    .weight(1f)
                    .background(Color(0xFFEADDFF)),
                title = stringResource(R.string.text_composable),
                body = stringResource(R.string.para1)
            )
            QuadrantArticle(
                modifier = Modifier
                    .weight(1f)
                    .background(Color(0xFFD0BCFF)),
                title = "Image composable",
                body = "Creates a composable that lays out and draws a given Painter class object."
            )
        }
        Row(Modifier.weight(1f)) {
            QuadrantArticle(
                modifier = Modifier.weight(1f).background(Color(0xFFB69DF8)),
                title = "Row composable",
                body = "A layout composable that places its children in a horizontal sequence."
            )
            QuadrantArticle(
                modifier = Modifier.weight(1f).background(Color(0xFFF6EDFF)),
                title = "Column composable",
                body = "A layout composable that places its children in a vertical sequence."
            )
        }
    }
}

//shows article for one quadrant
@Composable
fun QuadrantArticle(modifier: Modifier = Modifier, title: String, body: String) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(text = body)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShowQuadrant() {
    TaskManagerTheme {
        MakeQuadrant()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    TaskManagerTheme {
        ShowTaskCompletedScreen()
    }
}