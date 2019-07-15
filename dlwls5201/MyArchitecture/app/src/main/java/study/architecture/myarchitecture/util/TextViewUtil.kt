package study.architecture.myarchitecture.util

import android.widget.TextView
import androidx.core.content.ContextCompat
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

//전일대비
fun TextView.setTradeDiff(signedChangeRate: Double) {

    val color = if (signedChangeRate > 0) {
        ContextCompat.getColor(context, study.architecture.myarchitecture.R.color.diff_up)
    } else if (signedChangeRate < 0) {
        ContextCompat.getColor(context, study.architecture.myarchitecture.R.color.diff_down)
    } else {
        ContextCompat.getColor(context, study.architecture.myarchitecture.R.color.gray5)
    }

    this.setTextColor(color)

    val df = DecimalFormat("0.##")
    val rate = df.format(signedChangeRate * 100)
    this.text = "$rate%"
}

//거래대금
fun TextView.setTradeAmount(accTradePrice24h: Double) {

    val df = DecimalFormat("#,###")

    val amount = if(accTradePrice24h >= 1000000) {
        df.format(accTradePrice24h / 1000000)
    } else if(accTradePrice24h < 1000000 && accTradePrice24h >= 1000) {
        df.format(accTradePrice24h / 1000)
    } else {
        df.format(accTradePrice24h)
    }

    this.text = "$amount M"
}

