package com.architecturestudy.data.source

import com.architecturestudy.data.UpbitTicker

interface UpbitDataSource {
    interface GetTickerCallback {
        fun onTickerLoaded(marketPrice: List<UpbitTicker>)

        fun onDataNotAvailable(err: String?)
    }

    fun getMarketPrice(prefix: String, callback: GetTickerCallback)
}