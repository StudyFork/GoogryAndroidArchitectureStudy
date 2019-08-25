package com.jake.archstudy.network.response

import com.google.gson.annotations.SerializedName
import com.jake.archstudy.data.model.Ticker

data class TickerResponse(
    @SerializedName("acc_trade_price")
    val accTradePrice: Double,
    @SerializedName("acc_trade_price_24h")
    val accTradePrice24h: Double,
    @SerializedName("acc_trade_volume")
    val accTradeVolume: Double,
    @SerializedName("acc_trade_volume_24h")
    val accTradeVolume24h: Double,
    @SerializedName("change")
    val change: String,
    @SerializedName("change_price")
    val changePrice: Double,
    @SerializedName("change_rate")
    val changeRate: Double,
    @SerializedName("high_price")
    val highPrice: Double,
    @SerializedName("highest_52_week_date")
    val highest52WeekDate: String,
    @SerializedName("highest_52_week_price")
    val highest52WeekPrice: Double,
    @SerializedName("low_price")
    val lowPrice: Double,
    @SerializedName("lowest_52_week_date")
    val lowest52WeekDate: String,
    @SerializedName("lowest_52_week_price")
    val lowest52WeekPrice: Double,
    @SerializedName("market")
    val market: String,
    @SerializedName("opening_price")
    val openingPrice: Double,
    @SerializedName("prev_closing_price")
    val prevClosingPrice: Double,
    @SerializedName("signed_change_price")
    val signedChangePrice: Double,
    @SerializedName("signed_change_rate")
    val signedChangeRate: Double,
    @SerializedName("timestamp")
    val timestamp: Long,
    @SerializedName("trade_date")
    val tradeDate: String,
    @SerializedName("trade_date_kst")
    val tradeDateKst: String,
    @SerializedName("trade_price")
    val tradePrice: Double,
    @SerializedName("trade_time")
    val tradeTime: String,
    @SerializedName("trade_time_kst")
    val tradeTimeKst: String,
    @SerializedName("trade_timestamp")
    val tradeTimestamp: Long,
    @SerializedName("trade_volume")
    val tradeVolume: Double
) {

    fun toTicker(): Ticker {
        val market = market.substringAfter("-")
        val tradePrice = if (tradePrice < 1) String.format("%,.2f", tradePrice) else String.format(
            "%,d",
            tradePrice.toInt()
        )
        val signedChangeRate = String.format("%,.2f", signedChangeRate * 100) + "%"
        val accTradePrice = when {
            accTradePrice24h < 1F -> String.format("%,.8f", accTradePrice24h)
            accTradePrice24h in 1F..999F -> String.format("%,d", accTradePrice24h.toInt())
            accTradePrice24h in 1000F..999999F -> String.format(
                "%,d",
                accTradePrice24h.toInt() / 1000
            ) + "K"
            accTradePrice24h >= 1000000F -> String.format(
                "%,d",
                accTradePrice24h.toInt() / 1000000
            ) + "M"
            else -> String.format("%,f", accTradePrice24h)
        }

        val colorId = when (change) {
            "RISE" -> android.R.color.holo_red_light
            "EVEN" -> android.R.color.darker_gray
            "FALL" -> android.R.color.holo_blue_light
            else -> android.R.color.darker_gray
        }

        return Ticker(
            market,
            tradePrice,
            signedChangeRate,
            accTradePrice,
            colorId
        )
    }

}