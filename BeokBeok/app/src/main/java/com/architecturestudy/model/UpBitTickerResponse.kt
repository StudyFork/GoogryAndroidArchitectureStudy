package com.architecturestudy.model

import com.google.gson.annotations.SerializedName

data class UpBitTickerResponse(

    @field:SerializedName("market")
    val market: String? = null,

    @field:SerializedName("trade_price")
    val tradePrice: Double? = null,

    @field:SerializedName("change")
    val change: String? = null,

    @field:SerializedName("signed_change_rate")
    val signedChangeRate: Double? = null,

    @field:SerializedName("acc_trade_price_24h")
    val accTradePrice24h: Double? = null
)