package dev.daeyeon.gaasproject.data.source

import dev.daeyeon.gaasproject.data.Ticker

interface UpbitDataSource {
    fun getTicker(success: (list: List<Ticker>) -> Unit, fail: (msg: String) -> Unit)

    fun getMarkets(success: (markets: String) -> Unit, fail: (msg: String) -> Unit)
}