package com.android.studyfork.util

import com.android.studyfork.R
import java.text.DecimalFormat

/**
 * created by onemask
 */

val doubleFormat = DecimalFormat("#,##0.00000000")
val intFormat = DecimalFormat("#,###.##")

//현재가
fun String.filterTrade(tradePrice: Double?): String {
    return if (tradePrice ?: .0 > 1) {
        intFormat.format(tradePrice ?: 0)
    } else {
        doubleFormat.format(tradePrice ?: 0)
    }
}

//전일 대비
fun String.setTextColor(signedChangeRate: Double): Int {
    val color = when {
        (signedChangeRate > 0) -> return R.color.red
        signedChangeRate < 0 -> return R.color.blue
        else -> R.color.gray
    }
    return color
}

//전일 대비
fun String.setTradeDiff(signedChangeRate: Double): String {
    val df = DecimalFormat("0.##")
    val rate = df.format(signedChangeRate * 100)
    return "$rate%"
}
