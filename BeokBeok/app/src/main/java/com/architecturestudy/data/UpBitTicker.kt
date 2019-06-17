package com.architecturestudy.data

import com.google.gson.annotations.SerializedName

data class UpBitTicker(

    @SerializedName("market")
    val market: String? = null,

    @SerializedName("trade_price")
    val tradePrice: Double? = null,

    @SerializedName("change")
    val change: String? = null,

    @SerializedName("signed_change_rate")
    val signedChangeRate: Double? = null,

    @SerializedName("acc_trade_price_24h")
    val accTradePrice24h: Double? = null
)