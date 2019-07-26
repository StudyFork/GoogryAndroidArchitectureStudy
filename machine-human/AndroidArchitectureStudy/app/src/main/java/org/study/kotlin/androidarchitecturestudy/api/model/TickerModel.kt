package org.study.kotlin.androidarchitecturestudy.api.model

import com.google.gson.annotations.SerializedName

data class TickerModel(
    @SerializedName("acc_trade_price_24h")
    val accTradePrice24h: Double,
    @SerializedName("change_rate")
    val changeRate: Double,
    @SerializedName("market")
    val market: String,
    @SerializedName("trade_price")
    val tradePrice: Double,

    var convertAccTradePrice24h: String? = null,

    var convertChangeRate: String? = null,

    var convertMarket: String? = null,

    var convertTradePrice: String? = null
)