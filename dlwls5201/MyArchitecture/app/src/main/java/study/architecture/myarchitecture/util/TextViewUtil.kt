package study.architecture.myarchitecture.util

import android.widget.TextView
import java.text.DecimalFormat

//코인명
fun TextView.setCoinName(market: String) {
    val split = market.split("-")

    val name = if (split.size > 1) {
        split[1]
    } else {
        market
    }

    this.text = name
}

//현재가
fun TextView.setLast(tradePrice: Double) {

    val df = if (tradePrice >= 1) {
        DecimalFormat("#,###")
    } else {
        DecimalFormat("0.00000000")
    }

    this.text = df.format(tradePrice)
}


//거래대금
fun TextView.setTradeAmount(accTradePrice24h: Double) {

    val df = DecimalFormat("#,###")

    this.text = if (accTradePrice24h >= 1000000) {
        "${df.format(accTradePrice24h / 1000000)}M"
    } else if (accTradePrice24h < 1000000 && accTradePrice24h >= 1000) {
        "${df.format(accTradePrice24h / 1000)}K"
    } else {
        df.format(accTradePrice24h)
    }
}

