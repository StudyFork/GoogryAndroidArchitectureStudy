package com.example.seonoh.seonohapp.model

data class UseCoinModel(
    var market: String?,
    var tradePrice: String,
    var rate : String,
    var changeColor : Int,
    var accTradePrice24hPrice : String,
    var accTradePrice24hFormat : Int
)