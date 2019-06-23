package com.architecturestudy.data

import com.architecturestudy.data.source.UpbitDataSource
import com.architecturestudy.data.source.UpbitRetrofitDataSource

class UpbitRepository private constructor(
    private val upbitRetrofitDataSource: UpbitRetrofitDataSource
) : UpbitDataSource {

    override fun getMarketPrice(
        prefix: String,
        callback: UpbitDataSource.GetTickerCallback
    ) {

        upbitRetrofitDataSource.getMarketPrice(
            prefix,
            object : UpbitDataSource.GetTickerCallback {
                override fun onTickerLoaded(marketPrice: List<UpbitTicker>) {
                    callback.onTickerLoaded(marketPrice)
                }

                override fun onDataNotAvailable(t: Throwable) {
                    callback.onDataNotAvailable(t)
                }

            })
    }

    companion object {
        private var instance: UpbitRepository? = null

        operator fun invoke(upbitRetrofitDataSource: UpbitRetrofitDataSource): UpbitRepository {
            return instance ?: UpbitRepository(upbitRetrofitDataSource)
                .apply { instance = this }
        }

    }
}