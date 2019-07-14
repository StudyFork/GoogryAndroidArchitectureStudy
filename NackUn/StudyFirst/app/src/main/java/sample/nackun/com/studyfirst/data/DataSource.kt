package sample.nackun.com.studyfirst.data

import sample.nackun.com.studyfirst.vo.Ticker

interface DataSource {
    fun requestMarkets(
        marketLike: String,
        onTickersLoaded: (List<Ticker>) -> Unit,
        onError: (Throwable) -> Unit
    )
}