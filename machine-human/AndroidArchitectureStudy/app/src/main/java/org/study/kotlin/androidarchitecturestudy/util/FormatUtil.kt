package org.study.kotlin.androidarchitecturestudy.util

import android.icu.text.DecimalFormat
import org.study.kotlin.androidarchitecturestudy.api.model.TickerModel
import org.study.kotlin.androidarchitecturestudy.data.enum.TabTitle

object FormatUtil {
    val format = DecimalFormat("###,###")
    fun commaFormat(number: Number): String? =
        if (number is Double) {
            format.format(number)
        } else {
            format.format(number)
        }

    fun floatingEightPointFormat(number: Double) = String.format("%.8f", number)
    fun floatingThreePointFormat(number: Double) = String.format("%.3f", number)
    fun usdtFloatingPointFormat(number: Double) =
        if (number < 1)
            String.format("%.8f", number)
        else {
            commaFormat(number)
        }


    fun percentFormat(number: Double) = String.format("%.2f", number * 100) + "%"
    fun accTradePriceFormat(number: Double, market: String) =
        if (market == "KRW") {
            var num1 = number / 1000000
            val num2 = Math.round(num1)
            commaFormat(num2) + "M"
        } else {
            if (number > 999999) {
                var num1 = number / 1000
                val num2 = Math.round(num1)
                commaFormat(num2) + "K"
            } else {
                val num1 = Math.round(number)
                commaFormat(num1)
            }

        }

    fun convertTo(tickerList: TickerModel): TickerModel{
        val convertTickerList = TickerModel(0.toDouble(),0.toDouble(),0.toString(),0.toDouble())
        with(tickerList){
            convertTickerList.convertMarket = this.market.substringAfterLast("-")
            if (this.market.substringBeforeLast("-") == TabTitle.BTC.toString() || this.market.substringBeforeLast("-") == TabTitle.ETH.toString()) {
                convertTickerList.convertTradePrice = floatingEightPointFormat(this.tradePrice)
                convertTickerList.convertChangeRate = percentFormat(this.changeRate)
                convertTickerList.convertAccTradePrice24h = floatingThreePointFormat(this.accTradePrice24h)
            } else if (this.market.substringBeforeLast("-") == TabTitle.KRW.toString()) {
                convertTickerList.convertTradePrice = commaFormat(this.tradePrice)
                convertTickerList.convertChangeRate = percentFormat(this.changeRate)
                convertTickerList.convertAccTradePrice24h = accTradePriceFormat(this.accTradePrice24h, TabTitle.KRW.toString())
            } else {
                convertTickerList.convertTradePrice = usdtFloatingPointFormat(this.tradePrice)
                convertTickerList.convertChangeRate = percentFormat(this.changeRate)
                convertTickerList.convertAccTradePrice24h = accTradePriceFormat(this.accTradePrice24h, TabTitle.USDT.toString())
            }
        }
        return convertTickerList
    }
}
