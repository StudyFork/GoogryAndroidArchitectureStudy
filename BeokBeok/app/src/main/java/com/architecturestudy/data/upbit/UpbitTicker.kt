package com.architecturestudy.data.upbit

import com.google.gson.annotations.SerializedName

data class UpbitTicker(

    @SerializedName("market")
    val market: String?,

    @SerializedName("trade_price")
    val tradePrice: Double?,

    @SerializedName("signed_change_rate")
    val signedChangeRate: Double?,

    @SerializedName("acc_trade_price_24h")
    val accTradePrice24h: Double?
)