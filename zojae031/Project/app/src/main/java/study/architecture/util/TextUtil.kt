package study.architecture.util

import android.graphics.Color
import java.text.DecimalFormat

object TextUtil {
    fun getMarketName(market: String): String =
        market.substringAfter('-')

    fun getTradePrice(tradePrice: Double): String =
        if (tradePrice.toInt() > 0) {
            String.format("%,d", tradePrice.toInt())
        } else {
            String.format("%,f", tradePrice)
        }

    fun getChangeRate(changeRate: Double): String = String.format("%.2f%%", changeRate * 100)

    fun getAccTradePrice24h(accTradePrice: Double): String = with(DecimalFormat("#,###")) {
        if (accTradePrice >= 1000000) {
            return@with "${this.format(accTradePrice / 1000000)}M"
        } else if (accTradePrice < 1000000 && accTradePrice >= 1000) {
            return@with "${this.format(accTradePrice / 1000)}K"
        } else {
            return@with this.format(accTradePrice)
        }
    }

    fun getColorState(changeRate: Double): Int {
        return if (changeRate > 0) {
            Color.RED
        } else {
            Color.BLUE
        }
    }
}