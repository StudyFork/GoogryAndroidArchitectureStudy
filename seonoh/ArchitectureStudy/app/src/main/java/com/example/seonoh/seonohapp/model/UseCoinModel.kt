package com.example.seonoh.seonohapp.model

data class UseCoinModel(
    var market: String?,
    var tradePrice: String,
    var rate : String,
    var changeColor : Int,
    val accTradePrice_24h: Map<String,Any>
//    var accTradePrice24hPrice : Long,
//    var accTradePrice24hFormat : Number?
)