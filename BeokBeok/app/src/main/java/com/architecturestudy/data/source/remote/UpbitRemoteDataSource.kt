package com.architecturestudy.data.source.remote

import com.architecturestudy.common.MarketTypes
import com.architecturestudy.data.UpbitTicker
import com.architecturestudy.data.source.UpbitDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class UpbitRemoteDataSource constructor(
    private val retrofit: UpbitRemoteService
) : UpbitDataSource {

    override fun getMarketPrice(
        prefix: String,
        onSuccess: (List<UpbitTicker>) -> Unit,
        onFail: (Throwable) -> Unit
    ): Disposable =
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

    override fun saveTicker(upbitTicker: UpbitTicker): Disposable? =
        throw IllegalStateException("Not validate call")

    override fun sort(
        sortType: String,
        isDesc: Boolean,
        onSuccess: (List<UpbitTicker>) -> Unit,
        onFail: (Throwable) -> Unit
    ): Disposable = throw IllegalStateException("Not validate call")

    private fun getTickers(
        tickers: List<String?>?,
        onSuccess: (List<UpbitTicker>) -> Unit,
        onFail: (Throwable) -> Unit
    ): Disposable = retrofit.getTicker(tickers)
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
}