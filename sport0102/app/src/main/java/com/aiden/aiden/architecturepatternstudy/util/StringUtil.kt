package com.aiden.aiden.architecturepatternstudy.util

fun getKRWCommaNumber(number: Double): String {
    return if (number.toString().split(".")[1].toInt() > 0) {
        String.format("%1$,.1f", number)
    } else {
        String.format("%,d", number.toInt())
    }
}

fun getBTCETHCommaNumber(number: Double): String {
    return String.format("%.8f", number)
}

fun getUSDTCommaNumber(number: Double): String {
    if (number < 1) {
        return String.format("%.8f", number)
    }
    return if (number.toString().split(".")[1].toInt() > 0) {
        String.format("%1$,.2f", number)
    } else {
        String.format("%.8f", number)
    }
}

fun getPercentNumber(closingPrice: Double, tradePrice: Double): String {
    var percent = Math.abs(tradePrice - closingPrice) / closingPrice
    percent *= 100
    return if (closingPrice - tradePrice > 0) {
        "-${String.format("%.2f", percent)}%"
    } else {
        "${String.format("%.2f", percent)}%"
    }
}

