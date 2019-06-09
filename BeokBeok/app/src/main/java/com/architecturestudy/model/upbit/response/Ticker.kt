package com.architecturestudy.model.upbit.response

import com.google.gson.annotations.SerializedName

data class Ticker(

    @field:SerializedName("market")
    val market: String? = null,

    @field:SerializedName("trade_price")
    val tradePrice: Double? = null,

    @field:SerializedName("change_rate")
    val changeRate: Double? = null,

    @field:SerializedName("acc_trade_price_24h")
    val accTradePrice24h: Double? = null
)