package com.architecturestudy.data.source

import com.architecturestudy.data.UpbitTicker
import com.architecturestudy.data.source.local.UpbitLocalDataSource
import com.architecturestudy.data.source.remote.UpbitRemoteDataSource
import io.reactivex.disposables.Disposable

class UpbitRepository constructor(
    private val upbitLocalDataSource: UpbitLocalDataSource?,
    private val upbitRemoteDataSource: UpbitRemoteDataSource
) : UpbitDataSource {

    override fun getMarketPrice(
        prefix: String,
        onSuccess: (List<UpbitTicker>) -> Unit,
        onFail: (Throwable) -> Unit
    ): Disposable = upbitRemoteDataSource.getMarketPrice(
        prefix,
        onSuccess,
        onFail
    )

    override fun saveTicker(upbitTicker: UpbitTicker): Disposable? =
        upbitLocalDataSource?.saveTicker(upbitTicker)

    override fun sort(
        sortType: String,
        isDesc: Boolean,
        onSuccess: (List<UpbitTicker>) -> Unit,
        onFail: (Throwable) -> Unit
    ): Disposable? = upbitLocalDataSource?.sort(
        sortType,
        isDesc,
        onSuccess,
        onFail
    )
}