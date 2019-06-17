package com.architecturestudy.data

import com.architecturestudy.data.source.UpBitDataSource
import com.architecturestudy.data.source.UpBitRetrofitDataSource

class UpBitRepository(
    private val upBitRetrofitDataSource: UpBitRetrofitDataSource
) : UpBitDataSource {

    override fun getMarketPrice(prefix: String, callback: UpBitDataSource.GetTickerCallback) {

        upBitRetrofitDataSource.getMarketPrice(prefix, object : UpBitDataSource.GetTickerCallback {
            override fun onThickerLoaded(marketPrice: List<UpBitTicker>) {
                callback.onThickerLoaded(marketPrice)
            }

            override fun onDataNotAvailable(err: String?) {
                callback.onDataNotAvailable(err)
            }

        })
    }

    companion object {
        private var INSTANCE: UpBitRepository? = null

        @JvmStatic
        fun getInstance(upBitRetrofitDataSource: UpBitRetrofitDataSource): UpBitRepository {
            return INSTANCE ?: UpBitRepository(upBitRetrofitDataSource)
                .apply { INSTANCE = this }
        }
    }
}