package com.android.studyfork.network.model

import com.google.gson.annotations.SerializedName

data class TickerResponse(
    @SerializedName("market") val market: String,
    @SerializedName("trade_date") val tradeDate: String,
    @SerializedName("trade_time") val tradeTime: String,
    @SerializedName("trade_date_kst") val tradeDateKst: String,
    @SerializedName("trade_time_kst") val tradeTimeKst: String,
    @SerializedName("trade_timestamp") val tradeTimestamp: Long,
    @SerializedName("opening_price") val openingPrice: Double,
    @SerializedName("high_price") val highPrice: Double,
    @SerializedName("low_price") val lowPrice: Double,
    @SerializedName("trade_price") val tradePrice: Double,
    @SerializedName("prev_closing_price") val prevClosingPrice: Double,
    @SerializedName("change") val change: String,
    @SerializedName("change_price") val changePrice: Double,
    @SerializedName("change_rate") val changeRate: Double,
    @SerializedName("signed_change_price") val signedChangePrice: Double,
    @SerializedName("signed_change_rate") val signedChangeRate: Double,
    @SerializedName("trade_volume") val tradeVolume: Double,
    @SerializedName("acc_trade_price") val accTradePrice: Double,
    @SerializedName("acc_trade_price_24h") val accTradePrice24h: Double,
    @SerializedName("acc_trade_volume") val accTradeVolume: Double,
    @SerializedName("acc_trade_volume_24h") val accTradeVolume24h: Double,
    @SerializedName("highest_52_week_price") val highest52WeekPrice: Double,
    @SerializedName("highest_52_week_date") val highest52WeekDate: String,
    @SerializedName("lowest_52_week_price") val lowest52WeekPrice: Double,
    @SerializedName("lowest_52_week_date") val lowest52WeekDate: String,
    @SerializedName("timestamp") val timestamp: Long
) : TickerImpl {

    override fun toTicker() =
        Ticker(
            marketName = market.split("-")[1],
            currentTradePrice = tradePrice,
            beforeSignedChangeRate = signedChangeRate,
            totalTrade = accTradePrice24h
        )
}