package org.study.kotlin.androidarchitecturestudy.api.model


data class ConvertTickerModel(
    var convertMarket: String? = null,
    var convertChangeRate: String? = null,
    var convertTradePrice: String? = null,
    var convertAccTradePrice24h: String? = null
)
