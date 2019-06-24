package com.architecturestudy.data.upbit.source

import com.architecturestudy.data.upbit.UpbitTicker

interface UpbitDataSource {
    interface GetTickerCallback {
        fun onTickerLoaded(marketPrice: List<UpbitTicker>)

        fun onDataNotAvailable(t: Throwable)
    }

    fun getMarketPrice(
        prefix: String,
        callback: GetTickerCallback
    )
}