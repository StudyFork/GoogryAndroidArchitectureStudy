package com.example.mystudy.util

import android.graphics.Color
import com.example.mystudy.R

object FormatText {

    //코인명
    fun lastMarketName(marketName: String) =
        marketName.split("-")[1]


    //현재가
    fun formatTradePrice(tradePrice: Double) =
        when {
            tradePrice > 100 -> String.format("%,.0f", tradePrice)
            tradePrice > 10 -> String.format("%,.1f", tradePrice)
            tradePrice > 1 -> String.format("%,.2f", tradePrice)
            else -> String.format("%,.8f", tradePrice)
        }

    //전일대비
    fun formatSignedChangeRate(signedChangeRate: Double) =
        String.format("%,.2f", signedChangeRate * 100) + "%"

    fun formatRateColor(signedChangeRate: Double) =
        when {
            signedChangeRate > 0 -> R.color.diff_up
            signedChangeRate < 0 -> R.color.diff_down
            else -> R.color.black
        }

    //거래대금
    fun formatAccTradePrice24h(accTradePrice24h: Double, firstMarket: String) =
        when (firstMarket) {
            "KRW" -> String.format("%,.0f", accTradePrice24h / 1000000) + "M"

            "USDT" ->
                if (accTradePrice24h > 1000000)
                    String.format("%,.0f", accTradePrice24h / 1000) + "k"
                else
                    String.format("%,.0f", accTradePrice24h)

            else -> String.format("%,.3f", accTradePrice24h)

        }


}
