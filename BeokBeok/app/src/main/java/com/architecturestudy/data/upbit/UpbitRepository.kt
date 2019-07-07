package com.architecturestudy.data.upbit

import androidx.databinding.ObservableField
import com.architecturestudy.data.upbit.source.UpbitDataSource
import com.architecturestudy.data.upbit.source.UpbitRetrofitDataSource

class UpbitRepository private constructor(
    private val upbitRetrofitDataSource: UpbitRetrofitDataSource
) : UpbitDataSource {

    var marketPriceList = ObservableField<List<UpbitTicker>>()
    var throwable = ObservableField<Throwable>()

    override fun getMarketPrice(prefix: String) {
        upbitRetrofitDataSource.getMarketPrice(prefix)
    }

    companion object {
        private var instance: UpbitRepository? = null

        operator fun invoke(
            upbitRetrofitDataSource: UpbitRetrofitDataSource
        ): UpbitRepository = instance ?: UpbitRepository(upbitRetrofitDataSource)
            .apply { instance = this }

    }
}