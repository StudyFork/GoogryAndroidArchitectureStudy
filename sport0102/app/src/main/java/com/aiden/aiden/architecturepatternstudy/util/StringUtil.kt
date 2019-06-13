package com.aiden.aiden.architecturepatternstudy.util

fun getKrwCommaPrice(price: Double): String =
    if (price.toString().split(".")[1].toInt() > 0) {
        String.format("%1$,.1f", price)
    } else {
        String.format("%,d", price.toInt())
    }

fun getBtcEthCommaPrice(price: Double): String = String.format("%.8f", price)

fun getUsdtCommaPrice(price: Double): String {
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

fun getKrwTotalDealPrice(totalPrice: Double): String = "${String.format("%,d", (totalPrice / 1_000_000).toInt())} M"

fun getBtcEthTotalDealPrice(totalPrice: Double): String = String.format("%1$,.3f", totalPrice)

fun getUsdtTotalDealPrice(totalPrice: Double): String {
    if (totalPrice > 1_000_000) {
        return "${String.format("%,d", (totalPrice / 1_000).toInt())} K"
    } else {
        return String.format("%,d", totalPrice.toInt())
    }
}






