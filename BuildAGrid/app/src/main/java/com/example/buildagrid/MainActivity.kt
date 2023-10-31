package com.example.buildagrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.buildagrid.data.DataSource
import com.example.buildagrid.data.Topic
import com.example.buildagrid.ui.theme.BuildAGridTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuildAGridTheme { // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    MainApp()
                }
            }
        }
    }


}

@Composable
fun MainApp() {
    CourseCardList()
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CourseCardList() {
    LazyVerticalGrid(contentPadding = PaddingValues(8.dp),
                     columns = GridCells.Fixed(2),
                     horizontalArrangement = Arrangement.spacedBy(8.dp),
                     verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(DataSource.topics.size) { index ->
            CourseCard(
                modifier = Modifier,
                topic = DataSource.topics[index]
            )
        }
    }
}

@Composable
fun CourseCard(modifier: Modifier, topic: Topic) {
    Card(modifier = modifier.height(68.dp)) {
        Row {
            Image(
                painter = painterResource(id = topic.imageResource),
                contentDescription = null,
                modifier = Modifier.fillMaxHeight(),
                contentScale = ContentScale.Crop,

                )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp, bottom = 8.dp),
                    text = stringResource(id = topic.name),
                    style = MaterialTheme.typography.bodyMedium,
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        modifier = Modifier
                            .padding(start = 16.dp),
                        painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = null,
                    )
                    Text(
                        text = "${topic.courses}",
                        modifier = Modifier.padding(start = 8.dp),
                        style = MaterialTheme.typography.labelMedium,
                    )

                }

            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun CourseCardPreview() {
    BuildAGridTheme {
        CourseCard(
            modifier = Modifier.padding(8.dp),
            topic = Topic(R.drawable.business, 33, R.string.app_name)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BuildAGridTheme {}
}