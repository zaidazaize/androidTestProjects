package com.example.tipcalculator

import org.junit.Test
import java.text.NumberFormat
import kotlin.math.ceil
import org.junit.Assert.*

class TipCalculatorTest {
    @Test
    fun testCalculateTip_NoRoundUp() {
        val amount = 90.0
        val percentage = 15.0
        val roundUp = false
        val expected = NumberFormat.getCurrencyInstance().format(90.0/100.0*15.0)
        val actual = calculate(amount, percentage, roundUp)
        assertEquals(expected, actual)
    }

    @Test
    fun testCalculateTip_RoundUp() {
        val amount = 90.0
        val percentage = 15.0
        val roundUp = true
        val expected = NumberFormat.getCurrencyInstance().format(ceil(90.0/100.0*15.0))
        val actual = calculate(amount, percentage, roundUp)
        assertEquals(expected, actual)
    }

}