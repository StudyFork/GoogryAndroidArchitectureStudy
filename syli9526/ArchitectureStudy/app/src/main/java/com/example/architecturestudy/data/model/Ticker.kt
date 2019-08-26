package com.example.architecturestudy.data.model

data class Ticker(
    val currencyType: String,
    val coinName: String,
    val presentPrice: Double,
    val signedChangeRate: Double,
    val transactionAmount: Double
)