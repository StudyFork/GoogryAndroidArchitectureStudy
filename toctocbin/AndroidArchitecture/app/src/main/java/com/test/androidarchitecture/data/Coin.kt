package com.test.androidarchitecture.data

import com.google.gson.annotations.SerializedName

data class Coin(

    val market: String,

    @SerializedName("trade_price")
    val tradePrice: Double,

    @SerializedName("signed_change_rate")
    val signedChangeRate: Double,

    val change: String,

    @SerializedName("acc_trade_price_24h")
    val accTradePrice24h: Double
)