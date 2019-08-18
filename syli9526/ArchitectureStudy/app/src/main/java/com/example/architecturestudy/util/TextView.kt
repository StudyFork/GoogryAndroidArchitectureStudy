package com.example.architecturestudy.util

import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.architecturestudy.R
import java.text.DecimalFormat

val doubleFormat = DecimalFormat("#,##0.00000000")
val intFormat = DecimalFormat("#,###.##")

//현재가
fun TextView.filterTrade(tradePrice: Double?) {
    text = if (tradePrice ?: .0 > 1) {
        intFormat.format(tradePrice ?: 0)
    } else {
        doubleFormat.format(tradePrice ?: 0)
    }
}

//전일 대비
fun TextView.setTradeDiff(signedChangeRate: Double) {

    val color = when {
        (signedChangeRate > 0) -> ContextCompat.getColor(context, R.color.colorRed)
        signedChangeRate < 0 -> ContextCompat.getColor(context, R.color.colorBlue)
        else -> ContextCompat.getColor(context, R.color.colorGray)
    }
    setTextColor(color)

    val df = DecimalFormat("0.##")
    val rate = df.format(signedChangeRate * 100)
    text = "$rate%"
}

//거래대금
fun TextView.setTradeAmount(currency: String, accTradePrice24h: Double) {

    val amount: String = when (currency) {

        "KRW" -> {
            String.format("%,d", (accTradePrice24h / 1000000).toInt()) + " M"
        }
        "BTC" -> {
            String.format(
                "%,d", DecimalFormat("0.###").format(accTradePrice24h).split(".")[0].toInt()
            ) + "." + DecimalFormat("0.###").format(accTradePrice24h).split(".")[1]
        }
        "ETH" -> {
            String.format(
                "%,d", DecimalFormat("0.###").format(accTradePrice24h).split(".")[0].toInt()
            ) + "." + DecimalFormat("0.###").format(accTradePrice24h).split(".")[1]
        }
        "USDT" -> {
            String.format(
                "%,d",
                DecimalFormat("0.###").format(accTradePrice24h / 1000).split(".")[0].toInt()
            ) + " K"
        }

        else -> ""

    }

    text = "$amount"
}
