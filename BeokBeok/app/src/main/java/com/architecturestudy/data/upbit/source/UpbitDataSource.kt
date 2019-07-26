package com.architecturestudy.data.upbit.source

import com.architecturestudy.data.upbit.UpbitTicker
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