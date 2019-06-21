package ado.sabgil.studyproject.data.model

import ado.sabgil.studyproject.data.enums.BaseCurrency
import ado.sabgil.studyproject.data.remote.upbit.response.UpbitTickerResponse

data class Ticker private constructor(
    val id: String,
    val base: String,
    val coinName: String,
    val currentValue: String,
    val changeRate: String,
    val accTradePrice: String
) {

    companion object {
        private const val KILO = 1_000.0
        private const val MEGA = 1_000_000.0

        fun fromApiResponse(tickerResponse: UpbitTickerResponse): Ticker {
            val base = tickerResponse.market.substringBefore("-")

            val coinName = tickerResponse.market.substringAfter("-")

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

            val currentValue: String =
                tickerResponse.tradePrice.let {
                    when (base) {
                        BaseCurrency.KRW.name ->
                            if (it - Math.floor(it) > 0) {
                                BaseCurrency.KRW.decimalPattern.format(it)
                            } else {
                                BaseCurrency.KRW.bigDecimalPattern.format(it)
                            }

                        BaseCurrency.BTC.name ->
                            BaseCurrency.BTC.decimalPattern.format(it)

                        BaseCurrency.ETH.name ->
                            BaseCurrency.ETH.decimalPattern.format(it)

                        BaseCurrency.USDT.name ->
                            BaseCurrency.USDT.decimalPattern.format(it)

                        else ->
                            BaseCurrency.DEFAULT.decimalPattern.format(it)
                    }
                }

            return Ticker(
                tickerResponse.market,
                base,
                coinName,
                currentValue,
                changeRate,
                accTradePrice
            )
        }
    }
}