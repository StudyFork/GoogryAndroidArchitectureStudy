package com.architecturestudy.data.upbit.source.remote

import com.architecturestudy.data.common.MarketTypes
import com.architecturestudy.data.upbit.UpbitTicker
import com.architecturestudy.data.upbit.source.UpbitDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UpbitRemoteDataSource private constructor(
    private val retrofit: UpbitRemoteService
) : UpbitDataSource {

    override fun getMarketPrice(
        prefix: String,
        onSuccess: (List<UpbitTicker>) -> Unit,
        onFail: (Throwable) -> Unit
    ) {
        CompositeDisposable().add(
            retrofit.getMarkets()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val markets = it.body()
                    if (markets.isNullOrEmpty()) {
                        onFail(IllegalStateException("Data not validate"))
                    } else {
                        val tickers = markets
                            .asSequence()
                            .filter {
                                enumValues<MarketTypes>().any { data ->
                                    data.name == prefix
                                }
                            }
                            .filter { data -> data.market.startsWith(prefix) }
                            .map { data -> data.market }
                            .toList()
                        getTickers(
                            tickers,
                            onSuccess,
                            onFail
                        )
                    }
                }, {
                    onFail(it)
                })
        )
    }

    override fun saveTicker(upbitTicker: UpbitTicker) {
        // NO OP
    }

    override fun sort(
        sortType: String,
        onSuccess: (List<UpbitTicker>) -> Unit,
        onFail: (Throwable) -> Unit
    ) {
        // NO OP
    }

    private fun getTickers(
        tickers: List<String?>?,
        onSuccess: (List<UpbitTicker>) -> Unit,
        onFail: (Throwable) -> Unit
    ) {
        CompositeDisposable().add(
            retrofit.getTicker(tickers)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val responseTicker = it.body()
                    if (responseTicker.isNullOrEmpty()) {
                        onFail(IllegalStateException("Data is empty"))
                    } else {
                        onSuccess(responseTicker)
                    }
                }, {
                    onFail(it)
                })
        )
    }

    companion object {
        private var instance: UpbitRemoteDataSource? = null

        operator fun invoke(
            retrofit: UpbitRemoteService
        ): UpbitRemoteDataSource = instance ?: UpbitRemoteDataSource(retrofit)
            .apply { instance = this }
    }
}