package com.example.seonoh.seonohapp.model

data class UseCoinModel(
    var market: String?,
    var tradePrice: String,
    var signedChangeRate: Map<String,Any>,
    val accTradePrice_24h: String
)