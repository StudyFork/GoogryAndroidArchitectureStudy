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
        tabType: String,
        sortType: String,
        onSuccess: (List<UpbitTicker>) -> Unit,
        onFail: (Throwable) -> Unit
    )
}