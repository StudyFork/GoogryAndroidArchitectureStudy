package com.aiden.aiden.architecturepatternstudy.util

fun getKRWCommaPrice(price: Double): String = if (price.toString().split(".")[1].toInt() > 0) {
    String.format("%1$,.1f", price)
} else {
    String.format("%,d", price.toInt())
}

fun getBTCETHCommaPrice(price: Double): String = String.format("%.8f", price)

fun getUSDTCommaPrice(price: Double): String {
    if (price < 1) {
        return String.format("%.8f", price)
    }
    return if (price.toString().split(".")[1].toInt() > 0) {
        String.format("%1$,.2f", price)
    } else {
        String.format("%.8f", price)
    }
}

fun getPercent(closingPrice: Double, tradePrice: Double): String {
    var percent = Math.abs(tradePrice - closingPrice) / closingPrice
    percent *= 100
    return if (closingPrice - tradePrice > 0) {
        "-${String.format("%.2f", percent)}%"
    } else {
        "${String.format("%.2f", percent)}%"
    }
}

fun getKRWTotalDealPrice(totalPrice: Double): String = "${String.format("%,d", (totalPrice / 1000000).toInt())} M"

fun getBTCETHTotalDealPrice(totalPrice: Double): String = String.format("%1$,.3f", totalPrice)

fun getUSDTTotalDealPrice(totalPrice: Double): String {
    if (totalPrice > 1000000) {
        return "${String.format("%,d", (totalPrice / 1000).toInt())} K"
    } else {
        return String.format("%,d", totalPrice.toInt())
    }
}






