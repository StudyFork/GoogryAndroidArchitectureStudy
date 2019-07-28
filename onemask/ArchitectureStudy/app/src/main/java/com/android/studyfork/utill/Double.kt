package com.android.studyfork.utill

import java.text.DecimalFormat

/**
 * created by onemask
 */

val doubleFormat = DecimalFormat("#,##0.00000000")
val intFormat = DecimalFormat("#,###.##")


//현재가
fun Double.filterTrade(tradePrice: Double?): String {
    return when {
        tradePrice ?: .0 > 1 -> intFormat.format(tradePrice ?: 0).toString()
        else -> doubleFormat.format(tradePrice ?: 0).toString()
    }
}

//거래대금
fun Double.setTradeAmount(accTradePrice24h: Double): String {
    val df = DecimalFormat("#,###")
    val amount = when {
        (accTradePrice24h >= 1000000) -> df.format(accTradePrice24h / 1000000)
        (accTradePrice24h < 1000000 && accTradePrice24h >= 1000) -> df.format(accTradePrice24h / 1000)
        else -> df.format(accTradePrice24h)
    }
    return "$amount M"
}

//전일 대비
fun Double.setBeforeRate(signedChangeRate: Double): String {
    val df = DecimalFormat("0.##")
    val rate = df.format(signedChangeRate * 100)
    return "$rate%"
}
