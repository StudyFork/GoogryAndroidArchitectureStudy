package com.aiden.aiden.architecturepatternstudy.util

import com.aiden.aiden.architecturepatternstudy.api.model.TickerResponse
import com.aiden.aiden.architecturepatternstudy.data.enums.Market
import java.math.BigDecimal
import kotlin.math.abs

object StringUtil {

    private fun getKrwCommaPrice(price: BigDecimal) =
        String.format("%1$,.1f", price)

    private fun getBtcEthCommaPrice(price: Double) =
        String.format("%.8f", price)

    private fun getUsdtCommaPrice(price: Double): String {
        if (price < 1) {
            return String.format("%.8f", price)
        }
        return if (price.toString().split(".")[1].toInt() > 0) {
            String.format("%1$,.2f", price)
        } else {
            String.format("%.8f", price)
        }
    }

    private fun getPercent(closingPrice: Double, tradePrice: Double): String {
        var percent = abs(tradePrice - closingPrice) / closingPrice
        percent *= 100
        return if (closingPrice - tradePrice > 0) {
            "-${String.format("%.2f", percent)}%"
        } else {
            "${String.format("%.2f", percent)}%"
        }
    }

    private fun getKrwTotalDealPrice(totalPrice: Double) =
        "${String.format("%,d", (totalPrice / 1_000_000).toInt())} M"

    private fun getBtcEthTotalDealPrice(totalPrice: Double) =
        String.format("%1$,.3f", totalPrice)

    private fun getUsdtTotalDealPrice(totalPrice: Double) =
        if (totalPrice > 1_000_000) {
            "${String.format("%,d", (totalPrice / 1_000).toInt())} K"
        } else {
            String.format("%,d", totalPrice.toInt())
        }

    fun modifyTicker(ticker: TickerResponse): TickerResponse {

        with(ticker) {

            // 코인 이름
            coinName = market.split("-")[1]

            //  현재 가격
            nowPrice = if (market.startsWith(
                    Market.KRW.marketName,
                    true
                )
            ) {
                getKrwCommaPrice(BigDecimal(tradePrice))
            } else if (market.startsWith(
                    Market.BTC.marketName,
                    true
                ) || market.startsWith(
                    Market.ETH.marketName,
                    true
                )
            ) {
                getBtcEthCommaPrice(tradePrice)
            } else {
                getUsdtCommaPrice(tradePrice)
            }

            // 전일대비 퍼센트
            compareBeforePercentage = getPercent(
                prevClosingPrice,
                tradePrice
            )

            // 거래대금
            totalDealPrice = if (market.startsWith(
                    Market.KRW.marketName,
                    true
                )
            ) {
                getKrwTotalDealPrice(accTradePrice24h)
            } else if (market.startsWith(
                    Market.BTC.marketName,
                    true
                ) || market.startsWith(
                    Market.ETH.marketName,
                    true
                )
            ) {
                getBtcEthTotalDealPrice(accTradePrice24h)
            } else {
                getUsdtTotalDealPrice(accTradePrice24h)
            }

        }

        return ticker

    }

}