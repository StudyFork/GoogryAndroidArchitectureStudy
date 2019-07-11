package com.architecturestudy.data.upbit

import com.architecturestudy.data.upbit.source.UpbitDataSource
import com.architecturestudy.data.upbit.source.UpbitRetrofitDataSource

class UpbitRepository private constructor(
    private val upbitRetrofitDataSource: UpbitRetrofitDataSource
) : UpbitDataSource {

    override fun getMarketPrice(
        prefix: String,
        onSuccess: (List<UpbitTicker>) -> Unit,
        onFail: (Throwable) -> Unit
    ) {
        upbitRetrofitDataSource.getMarketPrice(
            prefix,
            onSuccess,
            onFail
        )
    }

    companion object {
        private var instance: UpbitRepository? = null

        operator fun invoke(
            upbitRetrofitDataSource: UpbitRetrofitDataSource
        ): UpbitRepository = instance ?: UpbitRepository(upbitRetrofitDataSource)
            .apply { instance = this }

    }
}