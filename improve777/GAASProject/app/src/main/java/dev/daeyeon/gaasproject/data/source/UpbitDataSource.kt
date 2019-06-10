package dev.daeyeon.gaasproject.data.source

import dev.daeyeon.gaasproject.data.Ticker

interface UpbitDataSource {

    var markets: String

    fun getTicker(
        baseCurrency: String,
        searchTicker: String,
        success: (tickerList: List<Ticker>) -> Unit,
        fail: (msg: String) -> Unit
    )

    fun getMarkets(success: (markets: String) -> Unit, fail: (msg: String) -> Unit)

    fun unsubscribeTicker()

    companion object {
        const val ALL_CURRENCY = "전체"
    }
}