package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme



class MainActivity : ComponentActivity() {

    val artWorkList = listOf(
        ArtWork(id = 1, name = "Art Work 1", artist = "Artist 1", image = R.drawable.dice_1),
        ArtWork(id = 2, name = "Art Work 2", artist = "Artist 2", image = R.drawable.dice_2),
        ArtWork(id = 3, name = "Art Work 3", artist = "Artist 3", image = R.drawable.dice_3),
        ArtWork(id = 4, name = "Art Work 4", artist = "Artist 4", image = R.drawable.dice_4),
        ArtWork(id = 5, name = "Art Work 5", artist = "Artist 5", image = R.drawable.dice_5),
        ArtWork(id = 6, name = "Art Work 6", artist = "Artist 6", image = R.drawable.dice_6)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                ArtSpaceTheme {
                ArtApp(artWorkList)
            }
        }
    }

}

@Composable
fun ArtApp(artWorkList: List<ArtWork>) { // A surface container using the 'background' color from the theme
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Screen(artWorkList)
    }
}

data class ArtWork(val id: Int, val name: String, val artist: String, val image: Int)

@Composable
fun Screen(artWorkList: List<ArtWork>) {
    var index by rememberSaveable { mutableStateOf(0) }
    ScreenItem(modifier = Modifier.fillMaxSize(), artWorkList[index], onNext = {
        if (index < artWorkList.size - 1) {
            index++
        } else {
            index = 0
        }
    }) {
        if (index > 0) {
            index--
        } else {
            index = artWorkList.size - 1
        }
    }

}

@Composable
fun ScreenItem(
    modifier: Modifier = Modifier, artWork: ArtWork, onNext: () -> Unit, onPrevious: () -> Unit
) {

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = 8.dp
        ) {

            Image(
                painter = painterResource(id = artWork.image),
                contentDescription = artWork.name,
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
                    .height(400.dp)
            )
        }

        Surface(
            color = MaterialTheme.colorScheme.secondaryContainer,
            modifier = Modifier
                .padding(bottom = 24.dp,top = 24.dp)
                .fillMaxWidth(0.75f)
        ) {

            Column(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.Start,
            ) {

                Text(
                    text = artWork.name,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = artWork.artist,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            Button(
                onClick = onPrevious,
                modifier = Modifier
                    .weight(1f)
                    .width(0.dp)
                    .padding(horizontal = 24.dp)
            ) {
                Text(text = "Previous")
            }
            Button(
                onClick = onNext,
                modifier = Modifier
                    .weight(1f)
                    .width(0.dp)
                    .padding(horizontal = 24.dp)
            ) {
                Text(text = "Next")
            }
        }

    }
}