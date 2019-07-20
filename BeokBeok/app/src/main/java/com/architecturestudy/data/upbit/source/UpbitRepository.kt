package com.architecturestudy.data.upbit.source

import com.architecturestudy.data.upbit.UpbitTicker
import com.architecturestudy.data.upbit.source.remote.UpbitRemoteDataSource

class UpbitRepository private constructor(
    private val upbitRemoteDataSource: UpbitRemoteDataSource
) : UpbitDataSource {

    override fun getMarketPrice(
        prefix: String,
        onSuccess: (List<UpbitTicker>) -> Unit,
        onFail: (Throwable) -> Unit
    ) {
        upbitRemoteDataSource.getMarketPrice(
            prefix,
            onSuccess,
            onFail
        )
    }

    companion object {
        private var instance: UpbitRepository? = null

        operator fun invoke(
            upbitRemoteDataSource: UpbitRemoteDataSource
        ): UpbitRepository = instance ?: UpbitRepository(upbitRemoteDataSource)
            .apply { instance = this }
    }
}