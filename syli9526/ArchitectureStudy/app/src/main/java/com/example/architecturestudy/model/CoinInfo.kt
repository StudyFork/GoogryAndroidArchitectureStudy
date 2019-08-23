package com.example.architecturestudy.model

data class CoinInfo(
    val currencyType: String,
    val coinName: String,
    val presentPrice: Double,
    val signedChangeRate: Double,
    val transactionAmount: Double
)