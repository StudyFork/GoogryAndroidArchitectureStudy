package com.architecturestudy.data.upbit.source.local

import com.architecturestudy.data.upbit.UpbitTicker
import com.architecturestudy.data.upbit.source.UpbitDataSource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UpbitLocalDataSource internal constructor(
    private val upbitTickerDao: UpbitTickerDao?
) : UpbitDataSource {
    override fun getMarketPrice(
        prefix: String,
        onSuccess: (List<UpbitTicker>) -> Unit,
        onFail: (Throwable) -> Unit
    ) {
        // NO OP
    }

    override fun saveTicker(upbitTicker: UpbitTicker) {
        CompositeDisposable().add(
            Observable.fromCallable {
                upbitTickerDao?.run {
                    insertTicker(upbitTicker)
                }
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        )
    }

    companion object {
        private var instance: UpbitLocalDataSource? = null

        operator fun invoke(
            upbitTickerDao: UpbitTickerDao?
        ): UpbitLocalDataSource = instance ?: UpbitLocalDataSource(upbitTickerDao)
            .apply { instance = this }
    }
}