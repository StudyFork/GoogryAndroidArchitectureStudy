package com.test.androidarchitecture.ext

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.test.androidarchitecture.R
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

fun TextView.setNowPriceText(price: Double) {
    text = when {
        price < 1 -> String.format("%.8f", price)
        else -> NumberFormat.getNumberInstance(Locale.US).format(price)
    }
}

fun TextView.setCoinNameText(market: String) {
    text = market.split("-")[1]
}

@SuppressLint("SetTextI18n")
fun TextView.setChangeRate(changeRate: Double, change: String) {
    val color: Int = when (change) {
        "RISE" -> ContextCompat.getColor(context, R.color.colorRed)
        "EVEN" -> ContextCompat.getColor(context, R.color.colorBlack)
        "FALL" -> ContextCompat.getColor(context, R.color.colorBlue)
        else -> ContextCompat.getColor(context, R.color.colorBlack)
    }
    setTextColor(color)

    text = """${String.format("%.2f", changeRate * 100)}%"""
}

fun TextView.setTradePriceText(price: Double){
    val df = DecimalFormat("#,###")
    text = when{
        price > 1000000 ->  df.format(price/1000000)+"M"
        price > 1000 ->  df.format(price/1000)+"K"
        else -> df.format(price)
    }
}


