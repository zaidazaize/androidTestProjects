package com.example.themingandanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.themingandanimation.ui.theme.ThemingAndAnimationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThemingAndAnimationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background) {
                }
            }
        }
    }
}


@Composable
fun AndroidAlien(modifier: Modifier = Modifier, color: Color) {
    Image(modifier = modifier, painter = painterResource(id = R.mipmap.ic_launcher_round),
          contentDescription = null)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ThemingAndAnimationTheme {
        AndroidAlien(color = MaterialTheme.colorScheme.secondary)
    }
}