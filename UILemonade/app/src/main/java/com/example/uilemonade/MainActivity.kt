package com.example.uilemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.uilemonade.ui.theme.UILemonadeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UILemonadeTheme { // A surface container using the 'background' color from the theme
                    LemonApp()
            }
        }
    }
}

enum class LemonadeState {
    PLUCK_LEMON, SQUEEZE_LEMON, LEMONADE_DRINK, EMPTY_GLASS
}

data class ScreenState(val image: Int, val desc: String, val action: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonApp() {
    var screenState by remember { mutableStateOf(LemonadeState.PLUCK_LEMON) }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text("Lemonade", fontWeight = FontWeight.Bold) },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )
    }) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ) {

        }
        LemonadeScreenWithImage(
            screenState = screenState
        ) { screenState = it }
    }
}

@Composable
fun LemonadeScreenWithImage(
    modifier: Modifier    = Modifier,
    screenState: LemonadeState,
    updateScreenState: (LemonadeState) -> Unit
) {
    var count by remember { mutableStateOf(0) }


    when (screenState) {
        LemonadeState.PLUCK_LEMON -> {
            LemonadeScreenWithImageAndText(screenState = ScreenState(
                R.drawable.lemon_tree, "Lemmon Tree", "Tap the lemon tree to select a lemon"
            ), updateScreenState = {
                updateScreenState(LemonadeState.SQUEEZE_LEMON)
                count = (2..4).random()
            })
        }

        LemonadeState.SQUEEZE_LEMON -> {
            LemonadeScreenWithImageAndText(screenState = ScreenState(
                R.drawable.lemon_squeeze,
                "Squeeze Lemon",
                "Keep tapping the lemon to squeeze it"
            ), updateScreenState = {
                count--
                if (count <= 0) updateScreenState(LemonadeState.LEMONADE_DRINK)
            }


            )
        }

        LemonadeState.LEMONADE_DRINK -> {
            LemonadeScreenWithImageAndText(screenState = ScreenState(
                R.drawable.lemon_drink, "Drink Lemonade", "Tap to drink the lemonade"
            ), updateScreenState = { updateScreenState(LemonadeState.EMPTY_GLASS) })
        }

        LemonadeState.EMPTY_GLASS -> {
            LemonadeScreenWithImageAndText(screenState = ScreenState(
                R.drawable.lemon_restart, "Empty Glass", "Tap to restart the process"
            ), updateScreenState = { updateScreenState(LemonadeState.PLUCK_LEMON) })
        }
    }
}

@Composable
fun LemonadeScreenWithImageAndText(
    modifier: Modifier = Modifier, screenState: ScreenState, updateScreenState: () -> Unit
) {

    Box(modifier = modifier) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Button(
                shape = RoundedCornerShape(
                    dimensionResource(R.dimen.button_corner_radius)
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer
                ),
                onClick = updateScreenState
            ) {
                Image(
                    painter = painterResource(id = screenState.image),
                    contentDescription = screenState.desc,
                    modifier = Modifier
                        .width(dimensionResource(id = R.dimen.button_image_width))
                        .height(dimensionResource(id = R.dimen.button_image_height))
                        .padding(dimensionResource(id = R.dimen.button_interior_padding))
                )
            }
            Spacer(Modifier.width(16.dp))
            Text(style = MaterialTheme.typography.bodyLarge, text = screenState.action)
        }
    }
}
