package com.example.dicerollercompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dicerollercompose.ui.theme.DiceRollerComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background) {
                    DiceRollerApp()
                }
            }
        }
    }
}

@Composable
fun DiceRollerApp(homeViewModal: HomeViewModal = viewModel()) {

    val diceState by homeViewModal.diceValue.collectAsState()
//    val unchangedData by homeViewModal.unchangedData.collectAsState()

    DiceRollerWithImage(modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
                        diceState,
//                        unchangedData = unchangedData,
                        list = homeViewModal.list,
                        homeViewModal::rollDice,
                        homeViewModal::updateUnchangedData)
}

@Composable
fun DiceRollerWithImage(modifier: Modifier = Modifier,
                        diceState: DiceState,
//                        unchangedData: String,
                        list: List<String>,
                        rollDice: () -> Unit,
                        updateUnchangedData: () -> Unit) {

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = diceState.imageState),
              contentDescription = "${diceState.diceValue}")
        Text(text = "${diceState.diceValue}", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.width(20.dp))
        Button(onClick = rollDice
        ) {
            Text(text = "Roll")
        }
        Spacer(modifier = Modifier.width(20.dp))
        Text(text = diceState.unchangedData)
        Button(onClick = updateUnchangedData) {
            Text(text = "Change Unchanged data")
        }
        Spacer(modifier =Modifier.width(20.dp))
        LazyColumn(){
           items(list){
               Card(modifier = Modifier.fillMaxWidth()) {
                   Text(text = it)
               }
           }
        }

    }
}