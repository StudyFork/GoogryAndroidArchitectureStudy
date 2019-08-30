package com.example.architecturestudy.data.model

data class Ticker(
    val currencyType: String,
    val coinName: String,
    val presentPrice: String,
    val signedChangeRate: String,
    val signedChangeRateColor: Int,
    val transactionAmount: String
)