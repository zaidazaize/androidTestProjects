package com.example.composearticle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composearticle.ui.theme.ComposeArticleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeArticleTheme {
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
fun MakeArticle(){
   ArticleCard(painterResource(id = R.drawable.bg_compose_background),
       stringResource(id = R.string.jetpack_compose_tutorial),
       stringResource(id = R.string.compose_tutorial_para_1),
       stringResource(id = R.string.compose_tutorial_para_2))
}

@Composable
fun ArticleCard(painter : Painter, title : String, para1 : String, para2 : String) {

    Column() {
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
        )
        Text(
            text = title,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(16.dp)
                .padding(bottom = 16.dp)
        )
        Text(
            text = para1,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )
        Text(
            text = para2,
            modifier = Modifier.padding(16.dp)
        )

    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ComposeArticleTheme {
        MakeArticle()
    }
}