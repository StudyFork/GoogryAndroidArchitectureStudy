package com.architecture.study.item

data class Ticker(
    val unitName: String,
    val coinName: String,
    val nowPrice: String,
    val compareYesterday: String,
    val compareYesterdayTextColor: Int,
    val transactionAmount: String
)