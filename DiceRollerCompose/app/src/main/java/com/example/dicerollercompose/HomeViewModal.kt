package com.example.dicerollercompose

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class DiceState(val diceValue: Int,
                     val imageState: Int,
                        val unchangedData: String = "unchanged"
                    )

class HomeViewModal : ViewModel() {

    private var _diceValue = MutableStateFlow(DiceState(1, R.drawable.dice_1))
    val diceValue = _diceValue.asStateFlow()

//    private var _unchangedData = MutableStateFlow("unchanged")
//    val unchangedData = _unchangedData.asStateFlow()
    val list = listOf("a", "b", "c", "d", "e", "f", "g", "h", "i", "j")
    fun rollDice() {
        val randomInt = (1..6).random()
        val drawableResource = when (randomInt) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        _diceValue.value = _diceValue.value.copy(diceValue = randomInt, imageState = drawableResource)
    }

    fun updateUnchangedData() {
        val chars = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        val unchangedData = (1..16)
                .map { chars.random() }
                .joinToString("")
        _diceValue.value = _diceValue.value.copy(unchangedData = unchangedData)
    }


}