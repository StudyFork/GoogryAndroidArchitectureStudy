package com.example.seonoh.seonohapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CurrentPriceInfoModel(

    @Expose
    @SerializedName("market")
    val market: String,
    @Expose
    @SerializedName("trade_date")
    val tradeDate: String,
    @Expose
    @SerializedName("trade_time")
    val tradeTime: String,
    @Expose
    @SerializedName("trade_date_kst")
    val tradeDateKst: String,
    @Expose
    @SerializedName("trade_time_kst")
    val tradeTimeKst: String,
    @Expose
    @SerializedName("trade_timestamp")
    val tradeTimeStamp: Long,
    @Expose
    @SerializedName("opening_price")
    val openingPrice: Double,
    @Expose
    @SerializedName("high_price")
    val highPrice: Double,
    @Expose
    @SerializedName("low_price")
    val lowPrice: Double,
    @Expose
    @SerializedName("trade_price")
    val tradePrice: Double,
    @Expose
    @SerializedName("prev_closing_price")
    val prevClosingPrice: Double,
    @Expose
    @SerializedName("change")
    val change: String,
    @Expose
    @SerializedName("change_price")
    val changePrice: Double,
    @Expose
    @SerializedName("change_rate")
    val changeRate: Double,
    @Expose
    @SerializedName("signed_change_price")
    val signedChangePrice: Double,
    @Expose
    @SerializedName("signed_change_rate")
    val signedChangeRate: Double,
    @Expose
    @SerializedName("trade_volume")
    val tradeVolume: Double,
    @Expose
    @SerializedName("acc_trade_price")
    val accTradePrice: Double,
    @Expose
    @SerializedName("acc_trade_price_24h")
    val accTradePrice_24h: String,
    @Expose
    @SerializedName("highest_52_week_price")
    val highest52WeekPrice: Double,
    @Expose
    @SerializedName("highest_52_week_date")
    val highest52WeekDate: String,
    @Expose
    @SerializedName("lowest_52_week_price")
    val lowest52WeekPrice: Double,
    @Expose
    @SerializedName("lowest_52_week_date")
    val lowest52WeekDate: String,
    @Expose
    @SerializedName("timestamp")
    val timestamp: Long
)