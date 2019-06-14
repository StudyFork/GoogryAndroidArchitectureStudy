package org.study.kotlin.androidarchitecturestudy.util

import android.icu.text.DecimalFormat

object FormatUtil {
    val format = DecimalFormat("###,###")
    fun commaDoubleFormat(number: Double) = format.format(number)
    fun commaIntFormat(number: Long) = format.format(number)
    fun floatingEightPointFormat(number: Double) = String.format("%.8f", number)
    fun floatingThreePointFormat(number: Double) = String.format("%.3f", number)
    fun usdtFloatingPointFormat(number: Double) =
        if (number < 1)
            String.format("%.8f", number)
        else {
            commaDoubleFormat(number)
        }


    fun percentFormat(number: Double) = String.format("%.2f", number * 100) + "%"
    fun accTradePriceFormat(number: Double, market: String) =
        if (market == "KRW") {
            var num1 = number / 1000000
            val num2 = Math.round(num1)
            commaIntFormat(num2) + "M"
        } else {
            if (number > 999999) {
                var num1 = number / 1000
                val num2 = Math.round(num1)
                commaIntFormat(num2) + "K"
            } else {
                val num1 = Math.round(number)
                commaIntFormat(num1)
            }

        }
}
