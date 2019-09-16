package com.jake.archstudy.data.model

import androidx.annotation.ColorRes

data class Ticker(
    val market: String,
    val tradePrice: String,
    val signedChangeRate: String,
    val accTradePrice24h: String,
    @ColorRes
    val colorRes: Int
)