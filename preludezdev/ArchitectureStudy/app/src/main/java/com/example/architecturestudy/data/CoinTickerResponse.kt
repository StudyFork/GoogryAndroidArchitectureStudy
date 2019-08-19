package com.example.architecturestudy.data

import android.graphics.Color
import com.example.architecturestudy.util.Util
import com.google.gson.annotations.SerializedName

data class CoinTickerResponse(
    @SerializedName("market")
    val market: String = "",
    @SerializedName("trade_price")
    val tradePrice: Double = 0.0,
    @SerializedName("signed_change_rate")
    val signedChangeRate: Double = 0.0,
    @SerializedName("acc_trade_price_24h")
    val accTradePriceH: Double = 0.0
) {
    private val oneThousand = 1_000
    private val oneHundredThousand = 100_000
    private val tenMillion = 10_000_000

    fun convertTickerIntoCoin(): Coin {
        //코인 이름
        val coinName = market.split("-")[1]

        //현재가
        val tradePrice = when {
            tradePrice > oneThousand ->
                Util.convertBigNumberToStdString(tradePrice.toInt())
            tradePrice > 2 ->
                String.format("%.2f", tradePrice)
            else ->
                String.format("%.8f", tradePrice)
        }

        //전일대비
        val signedChangeRate = String.format("%.2f", signedChangeRate * 100) + "%"

        //전일대비 색깔 지정
        val coinColor = if (signedChangeRate.startsWith('-')) Color.BLUE else Color.RED

        //거래대금
        val accTradePriceH = when {
            accTradePriceH > tenMillion -> {
                Util.convertBigNumberToStdString((accTradePriceH / 1000000).toInt()) + "M"
            }

            accTradePriceH > oneHundredThousand ->
                Util.convertBigNumberToStdString(accTradePriceH.toInt() / 1000) + "k"

            accTradePriceH > oneThousand ->
                Util.convertBigNumberToStdString(accTradePriceH.toInt())

            else ->
                String.format("%.3f", accTradePriceH)
        }

        return Coin(coinName, tradePrice, signedChangeRate, accTradePriceH, coinColor)
    }
}