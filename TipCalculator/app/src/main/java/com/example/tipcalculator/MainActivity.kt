package com.example.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipcalculator.ui.theme.TipCalculatorTheme
import kotlinx.coroutines.launch
import org.jetbrains.annotations.VisibleForTesting
import java.text.DecimalFormat
import java.text.NumberFormat

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalculatorTheme {

                TipApp()
            }
        }
    }
}

@Composable
fun TipApp() { // A surface container using the 'background' color from the theme
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        InputScreen(Modifier.fillMaxSize())

    }
}

@Preview(showBackground = true, showSystemUi = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputScreen(modifier: Modifier = Modifier) {
    var billAmount by rememberSaveable { mutableStateOf("") }
    var percentage by rememberSaveable { mutableStateOf("") }
    var roundUp by rememberSaveable { mutableStateOf(false) }
    var tipAmount by rememberSaveable { mutableStateOf("") }
//    val scope = rememberCoroutineScope()
    val billAmountDouble = billAmount.toDoubleOrNull() ?: 0.0
    val percentageDouble = percentage.toDoubleOrNull() ?: 0.0
    tipAmount = calculate(billAmountDouble, percentageDouble, roundUp)

    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp)
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 16.dp, top = 40.dp),
            text = "Calculate Tip",
        )

        EditNumberText(
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth(),
            value = billAmount,
            onValueChange = {
                billAmount = it
                //                scope.launch { calculate() }
            },
            label = "Bill Amount",
            leadingIcon = {
                Icon(
                    painterResource(id = R.drawable.round_money_24), contentDescription = "money"
                )
            },
            imeAction = ImeAction.Next
        )

        EditNumberText(modifier = Modifier.fillMaxWidth(), value = percentage, onValueChange = {
            percentage = it
            //            scope.launch {
            //                calculate()
            //            }
        }, label = "Tip Percentage", leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.round_percent_24),
                contentDescription = "Percent"
            )
        }, imeAction = ImeAction.Done)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "Round up tip ?",
                style = MaterialTheme.typography.bodyMedium,
            )
            Switch(checked = roundUp, onCheckedChange = {
                roundUp = it
                //                scope.launch {
                //                    calculate()
                //                }
            })
        }

        if (tipAmount.isNotEmpty())
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 24.dp, bottom = 40.dp),
                text = stringResource(R.string.tip_amount, tipAmount),
                style = MaterialTheme.typography.headlineMedium
            )
    }



}
@VisibleForTesting
internal fun calculate(bill: Double, percent: Double = 15.0,roundUp: Boolean = false): String {
    var tip = bill * (percent / 100)
    tip = if (roundUp) {
        kotlin.math.ceil(tip) //                tip = NumberFormat.getCurrencyInstance().format(tip)
    } else {
        val decimalFormat = DecimalFormat("#.##")
        decimalFormat.format(tip).toDouble()
    }
    return if (tip == 0.0) {
        ""
    } else NumberFormat.getCurrencyInstance().format(tip)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNumberText(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    imeAction: ImeAction = ImeAction.Next,
    onImeAction: () -> Unit = {}
) {
    TextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Decimal, imeAction = imeAction
        ),
        label = { Text(label) },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        singleLine = true,
        modifier = modifier
    )
}

