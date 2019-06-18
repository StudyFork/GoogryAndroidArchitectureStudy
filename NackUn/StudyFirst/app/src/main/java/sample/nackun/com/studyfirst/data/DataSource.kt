package sample.nackun.com.studyfirst.data

import sample.nackun.com.studyfirst.vo.Ticker

interface DataSource {
    var tickers: ArrayList<Ticker>
    fun requestMarkets(marketLike: String)
    fun requestTickers(query: String)
}