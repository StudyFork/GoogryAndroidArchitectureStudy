package com.example.architecturestudy.data

import android.graphics.Color
import com.example.architecturestudy.util.Util
import com.example.architecturestudy.util.Util.MILLION
import com.example.architecturestudy.util.Util.THOUSAND
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
    fun convertTickerIntoCoin(): Coin {
        //코인 이름
        val coinName = market.split("-")[1]

        //현재가
        val mTradePrice = when {
            tradePrice > THOUSAND ->
                Util.convertBigNumberToStdString(tradePrice.toInt())
            tradePrice > 2 ->
                String.format("%.2f", tradePrice)
            else ->
                String.format("%.8f", tradePrice)
        }

        //전일대비
        val mSignedChangeRate = String.format("%.2f", signedChangeRate * 100) + "%"

        //거래대금
        val mAccTradePriceH = when {
            accTradePriceH > 10 * MILLION -> {
                Util.convertBigNumberToStdString((accTradePriceH / MILLION).toInt()) + "M"
            }

            accTradePriceH > 100 * THOUSAND ->
                Util.convertBigNumberToStdString(accTradePriceH.toInt() / THOUSAND) + "k"

            accTradePriceH > THOUSAND ->
                Util.convertBigNumberToStdString(accTradePriceH.toInt())

            else ->
                String.format("%.3f", accTradePriceH)
        }

        //전일대비 색깔 지정
        val coinColor = if (mSignedChangeRate.startsWith('-')) Color.BLUE else Color.RED

        return Coin(
            coinName,
            mTradePrice,
            tradePrice,
            mSignedChangeRate,
            signedChangeRate,
            mAccTradePriceH,
            accTradePriceH,
            coinColor
        )
    }
}