package com.architecturestudy.data.source

import com.architecturestudy.data.UpbitTicker
import io.reactivex.disposables.Disposable

interface UpbitDataSource {
    fun getMarketPrice(
        prefix: String,
        onSuccess: (List<UpbitTicker>) -> Unit,
        onFail: (Throwable) -> Unit
    ): Disposable

    fun saveTicker(upbitTicker: UpbitTicker): Disposable?

    fun sort(
        sortType: String,
        isDesc: Boolean,
        onSuccess: (List<UpbitTicker>) -> Unit,
        onFail: (Throwable) -> Unit
    ): Disposable?
}