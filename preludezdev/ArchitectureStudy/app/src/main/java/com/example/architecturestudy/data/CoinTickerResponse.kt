package com.example.architecturestudy.data

data class CoinTickerResponse(
    val market: String = "",
    val trade_price: Double = 0.0,
    val signed_change_rate: Double = 0.0,
    val acc_trade_price_24h: Double = 0.0
)