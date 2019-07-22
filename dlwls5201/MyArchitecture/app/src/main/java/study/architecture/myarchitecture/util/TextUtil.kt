package study.architecture.myarchitecture.util

import java.text.DecimalFormat

object TextUtil {

    //코인명
    fun getCoinName(market: String): String {
        val split = market.split("-")

        return if (split.size > 1) {
            split[1]
        } else {
            market
        }
    }

    //현재가
    fun getLast(tradePrice: Double): String {

        val df = if (tradePrice >= 1) {
            DecimalFormat("#,###")
        } else {
            DecimalFormat("0.00000000")
        }

        return df.format(tradePrice)
    }

    //전일대비
    fun getTradeDiff(signedChangeRate: Double): String {
        val df = DecimalFormat("0.##")
        val rate = df.format(signedChangeRate * 100)

        return "$rate%"
    }

    //거래대금
    fun getTradeAmount(accTradePrice24h: Double): String {
        val df = DecimalFormat("#,###")

        return if (accTradePrice24h >= 1000000) {
            "${df.format(accTradePrice24h / 1000000)}M"
        } else if (accTradePrice24h < 1000000 && accTradePrice24h >= 1000) {
            "${df.format(accTradePrice24h / 1000)}K"
        } else {
            df.format(accTradePrice24h)
        }
    }
}
