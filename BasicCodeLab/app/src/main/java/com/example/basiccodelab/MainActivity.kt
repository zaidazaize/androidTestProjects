package com.example.basiccodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basiccodelab.ui.theme.BasicCodeLabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicCodeLabTheme {
                // A surface container using the 'background' color from the theme
               MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier){
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
       Greeting(name = "Android","how are you?")
    }
}

@Composable
fun Greeting(name: String,message : String) {
    Surface(color=MaterialTheme.colorScheme.primary) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(text = "Hello $name!")
            Text(text = message, )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BasicCodeLabTheme {
        Greeting("Android","how are you?")
    }
}