package com.test.androidarchitecture.data

import com.google.gson.annotations.SerializedName

data class Coin(

    val market: String,

    @SerializedName("tradePrice")
    val tradePrice: Double,

    @SerializedName("signedChangeRate")
    val signedChangeRate: Double,

    val change: String,

    @SerializedName("accTradePrice24h")
    val accTradePrice24h: Double
)