package ado.sabgil.studyproject.data.model

import ado.sabgil.studyproject.data.remote.upbit.response.UpbitTickerResponse

data class Ticker private constructor(
    val id: String,
    val base: String,
    val coinName: String,
    val currentValue: Double,
    val changeRate: Double,
    val accTradePrice: Double
) {

    companion object {
        fun fromApiResponse(tickerResponse: UpbitTickerResponse): Ticker {
            val base = tickerResponse.market.substringBefore("-")

            val coinName = tickerResponse.market.substringAfter("-")

            return Ticker(
                tickerResponse.market,
                base,
                coinName,
                tickerResponse.tradePrice,
                tickerResponse.signedChangeRate,
                tickerResponse.accTradePrice
            )
        }
    }
}