package dev.daeyeon.gaasproject.data.source

interface UpbitDataSource {
    fun getTicker(success: (list: List<Ticker>) -> Unit, fail: (msg: String) -> Unit)

    fun getMarkets(success: (markets: String) -> Unit, fail: (msg: String) -> Unit)
}