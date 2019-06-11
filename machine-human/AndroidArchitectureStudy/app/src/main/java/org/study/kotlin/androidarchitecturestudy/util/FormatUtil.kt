package org.study.kotlin.androidarchitecturestudy.util

import java.text.DecimalFormat

object FormatUtil {
    val format = DecimalFormat("###,###")

    fun numberCommaFormat(number: Double) = format.format(number).toString()
    fun tradPriceValueFloatingPointFormat(number: Double) = String.format("%.8f", number)

    fun numberPercentFormat(number: Double) = String.format("%.2f", number * 100) + "%"


}