package com.example.architecturestudy.ui

data class CoinInfo(
    val currencyType: String,
    val coinName: String,
    val presentPrice: String,
    val compareWithYesterday: String,
    val compareTextColor: Int,
    val transactionAmount: String
)