package sample.nackun.com.studyfirst.util

import android.graphics.Color
import sample.nackun.com.studyfirst.vo.Ticker

object TickerFormatter {
    fun convertTo(item: Ticker): Map<String, String> {
        val differPrice =
            compareToBefore(item.tradePrice, item.prevClosingPrice)
        val convertItem =
            mapOf<String, String>(
                "tickerName" to getTickerName(item.market),
                "currentPrice" to getCurrentPrice(item.tradePrice),
                "comparePrice" to getComparePrice(differPrice),
                "compareColor" to getCompareColor(differPrice),
                "changePrice" to getChangePrice(item.accTradePrice24h)
            )
        return convertItem
    }


    private fun getTickerName(tickerName: String) =
        tickerName.substring(tickerName.indexOf("-") + 1, tickerName.length)

    private fun getCurrentPrice(currentPrice: Double): String {
        return when {
            currentPrice > 10 -> String.format("%,d", currentPrice.toInt())
            currentPrice > 1 -> String.format("%,.2f", currentPrice)
            currentPrice < 1 -> String.format("%,.8f", currentPrice)
            else -> String.format("%,d", currentPrice.toInt())
        }
    }

    private fun compareToBefore(currentPrice: Double, beforePrice: Double) =
        (currentPrice - beforePrice) / beforePrice * 100

    private fun getComparePrice(differPrice: Double) =
        String.format("%.2f", differPrice) + "%"

    private fun getCompareColor(differPrice: Double): String {
        return when {
            differPrice > 0 -> Color.RED.toString()
            differPrice < 0 -> Color.BLUE.toString()
            else -> Color.BLACK.toString()
        }
    }

    private fun getChangePrice(changePrice: Double): String {
        return when {
            changePrice / 1_000_000 > 50 ->
                String.format("%,d", (changePrice / 1_000_000).toInt()) + " M"
            changePrice / 1_000 > 1_000 ->
                String.format("%,d", (changePrice / 1_000).toInt()) + " K"
            changePrice > 1_000 ->
                String.format("%,d", changePrice.toInt())
            else ->
                String.format("%,.3f", changePrice)
        }
    }
}