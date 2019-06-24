package com.namjackson.archstudy.data.model

import com.namjackson.archstudy.data.source.remote.upbit.response.UpbitTickerResponse

data class Ticker(
    val market: String,
    val name: String,
    val tradePrice: String,
    val changeRate: String,
    val change: String,
    val tradeVolume: String
) {

    companion object {
        private const val K = 1_000L
        private const val M = 1_000_000L
        private const val RISE = "RISE"
        private const val FALL = "FALL"

        fun from(tickerResponse: UpbitTickerResponse): Ticker {

            val changeRate =
                (Math.floor(tickerResponse.signedChangeRate * 10_000.0) / 100.0).toString() + "%"

            val tradeVolume =
                when {
                    (tickerResponse.accTradePrice24h.toLong() > M) -> (tickerResponse.accTradePrice24h / M).toInt().toString() + "M"
                    (tickerResponse.accTradePrice24h.toLong() > K) -> (tickerResponse.accTradePrice24h / K).toInt().toString() + "K"
                    else -> tickerResponse.accTradePrice24h.toInt().toString()
                }


            return Ticker(
                tickerResponse.market.substringBefore("-"),
                tickerResponse.market.substringAfter("-"),
                tickerResponse.tradePrice.toBigDecimal().toString().format(".8f"),
                changeRate,
                tickerResponse.change,
                tradeVolume
            )

        }
    }
}
