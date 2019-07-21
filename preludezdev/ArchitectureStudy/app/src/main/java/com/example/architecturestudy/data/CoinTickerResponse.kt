package com.example.architecturestudy.data

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
)