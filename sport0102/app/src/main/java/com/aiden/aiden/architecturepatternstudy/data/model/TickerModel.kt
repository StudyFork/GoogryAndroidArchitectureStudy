package com.aiden.aiden.architecturepatternstudy.data.model

data class TickerModel(
    val coinName: String?,
    val nowPrice: String?,
    val prevClosingPrice: Double,
    val tradePrice: Double,
    val compareBeforePercentage: String?,
    val totalDealPrice: String?
)