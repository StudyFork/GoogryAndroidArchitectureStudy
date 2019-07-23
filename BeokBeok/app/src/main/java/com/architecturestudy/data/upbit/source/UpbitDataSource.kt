package com.architecturestudy.data.upbit.source

import com.architecturestudy.data.upbit.UpbitTicker

interface UpbitDataSource {
    fun getMarketPrice(
        prefix: String,
        onSuccess: (List<UpbitTicker>) -> Unit,
        onFail: (Throwable) -> Unit
    )

    fun saveTicker(upbitTicker: UpbitTicker)

    fun sort(
        sortType: String,
        isDesc: Boolean,
        onSuccess: (List<UpbitTicker>) -> Unit,
        onFail: (Throwable) -> Unit
    )
}