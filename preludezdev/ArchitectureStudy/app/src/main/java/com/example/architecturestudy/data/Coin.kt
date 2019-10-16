package com.example.architecturestudy.data

data class Coin(
    val market: String = "",
    val tradePrice: String = "",
    val tradePriceVal: Double = 0.0,
    val signedChangeRate: String = "",
    val signedChangeRateVal: Double = 0.0,
    val accTradePriceH: String = "",
    val accTradePriceHVal: Double = 0.0,
    val coinColor: Int = 0
)