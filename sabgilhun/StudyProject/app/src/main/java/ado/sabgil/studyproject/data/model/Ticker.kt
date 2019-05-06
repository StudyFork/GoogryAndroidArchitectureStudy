package ado.sabgil.studyproject.data.model

import ado.sabgil.studyproject.data.remote.upbit.response.UpbitTickerResponse

data class Ticker private constructor(
    val coinName: String,
    val currentValue: String,
    val changeRate: String,
    val accTradePrice: String
) {

    companion object {
        private const val KILO = 1_000.0
        private const val MEGA = 1_000_000.0

        fun from(tickerResponse: UpbitTickerResponse): Ticker {
            val changeRate =
                (Math.floor(tickerResponse.signedChangeRate * 10_000.0) / 100.0).toString() + "%"

            val accTradePrice =
                when {
                    tickerResponse.accTradePrice > MEGA ->
                        Math.floor(tickerResponse.accTradePrice / MEGA).toInt().toString() + "M"
                    tickerResponse.accTradePrice > KILO ->
                        Math.floor(tickerResponse.accTradePrice / KILO).toInt().toString() + "K"
                    else ->
                        Math.floor(tickerResponse.accTradePrice).toInt().toString()
                }

            return Ticker(
                tickerResponse.market,
                tickerResponse.tradePrice.toInt().toString(),
                changeRate,
                accTradePrice
            )
        }
    }
}