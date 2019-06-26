package sample.nackun.com.studyfirst.util

import sample.nackun.com.studyfirst.vo.Ticker

object TickerFormatter {
    fun convertTo(item: Ticker): Map<String, String> {
        val differPrice = compareToBefore(item.tradePrice, item.prevClosingPrice)
        val convertItem =
            mutableMapOf(
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

    private fun getCurrentPrice(currentPrice: Double) : String {
        when {
            currentPrice > 10 -> return String.format("%,d", currentPrice.toInt())
            currentPrice > 1 -> return String.format("%,.2f", currentPrice)
            currentPrice < 1 -> return String.format("%,.8f", currentPrice)
            else -> return String.format("%,d", currentPrice.toInt())
        }
    }

    private fun compareToBefore(currentPrice: Double, beforePrice: Double) =
        Math.abs(currentPrice - beforePrice) / beforePrice * 100

    private fun getComparePrice(differPrice: Double) =
        String.format("%.2f", differPrice) + "%"

    private fun getCompareColor(differPrice: Double): String{
        when {
            differPrice > 0 -> return "RED"
            differPrice == 0.0 -> return "BLACK"
            else -> return "BLUE"
        }
    }

    private fun getChangePrice(changePrice: Double):String{
        when{
            changePrice/1_000_000 > 50 ->
                return String.format("%,d", (changePrice / 1_000_000).toInt()) + " M"
            changePrice/1_000 > 1_000 ->
                return String.format("%,d", (changePrice / 1_000).toInt()) + " K"
            changePrice > 1_000 ->
                return String.format("%,d", changePrice.toInt())
            else ->
                return String.format("%,.3f", changePrice)
        }
    }
}