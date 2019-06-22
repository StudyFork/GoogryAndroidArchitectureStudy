package com.architecturestudy.data

import com.architecturestudy.data.source.UpbitDataSource
import com.architecturestudy.data.source.UpbitRetrofitDataSource

class UpbitRepository(
    private val upBitRetrofitDataSource: UpbitRetrofitDataSource
) : UpbitDataSource {

    override fun getMarketPrice(prefix: String, callback: UpbitDataSource.GetTickerCallback) {

        upBitRetrofitDataSource.getMarketPrice(prefix, object : UpbitDataSource.GetTickerCallback {
            override fun onTickerLoaded(marketPrice: List<UpbitTicker>) {
                callback.onTickerLoaded(marketPrice)
            }

            override fun onDataNotAvailable(err: String?) {
                callback.onDataNotAvailable(err)
            }

        })
    }

    companion object {
        private var INSTANCE: UpbitRepository? = null

        @JvmStatic
        fun getInstance(upBitRetrofitDataSource: UpbitRetrofitDataSource): UpbitRepository {
            return INSTANCE ?: UpbitRepository(upBitRetrofitDataSource)
                .apply { INSTANCE = this }
        }
    }
}