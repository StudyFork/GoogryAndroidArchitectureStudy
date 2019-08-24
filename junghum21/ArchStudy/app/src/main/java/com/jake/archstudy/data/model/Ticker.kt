package com.jake.archstudy.data.model

data class Ticker(
    val market: String,
    val tradePrice: String,
    val change: String,
    val signedChangeRate: String,
    val accTradePrice24h: String
)