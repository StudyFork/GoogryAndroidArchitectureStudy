package com.jake.archstudy.ext

import android.annotation.SuppressLint
import android.widget.TextView

fun TextView.setCoinName(market: String) {
    text = market.split("-")[1]
}

fun TextView.setCurrentPrice(price: Double) {
    text = if (price < 1) String.format("%,.2f", price) else String.format("%,d", price.toInt())
}

@SuppressLint("SetTextI18n")
fun TextView.setChangeRate(rate: Double) {
    val color = context.getColor(
        if (rate > 0) android.R.color.holo_red_light else android.R.color.holo_blue_light
    )
    setTextColor(color)
    text = String.format("%,.2f", rate * 100) + "%"
}

fun TextView.setTradePrice(price: Double) {
    text = when {
        price < 1F -> String.format("%,.2f", price)
        price in 1F..999F -> String.format("%,d", price.toInt())
        price in 1000F..999999F -> String.format("%,d", price.toInt() / 1000) + "K"
        price > 1000000F -> String.format("%,d", price.toInt() / 1000000) + "M"
        else -> String.format("%,f", price)
    }
}