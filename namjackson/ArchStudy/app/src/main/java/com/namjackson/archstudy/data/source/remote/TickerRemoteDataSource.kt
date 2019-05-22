package com.namjackson.archstudy.data.source.remote

import com.namjackson.archstudy.data.model.Ticker

interface TickerRemoteDataSource {


    fun getMarketAll(
        baseCurrency: String,
        success: (markets: String) -> Unit,
        fail: (msg: String) -> Unit
    )

    fun getTickers(
        markets: String,
        success: (tickers: List<Ticker>) -> Unit,
        fail: (msg: String) -> Unit
    )

}