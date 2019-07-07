package com.architecturestudy.data.upbit.source

import com.architecturestudy.data.common.MarketTypes
import com.architecturestudy.data.upbit.UpbitRepository
import com.architecturestudy.data.upbit.service.UpbitRetrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UpbitRetrofitDataSource private constructor() : UpbitDataSource {

    private val retrofit = UpbitRetrofit.retrofit
    private val upbitRepository = UpbitRepository(this)

    override fun getMarketPrice(prefix: String) {
        CompositeDisposable().add(
            retrofit.getMarkets()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val markets = it.body() ?: return@subscribe
                    val tickers = markets
                        .asSequence()
                        .filter {
                            enumValues<MarketTypes>().any { data ->
                                data.markets == prefix
                            }
                        }
                        .filter { data -> data.market!!.startsWith(prefix) }
                        .map { data -> data.market }
                        .toList()
                    getTickers(tickers)
                }, {
                    upbitRepository.throwable.set(it)
                })
        )
    }

    private fun getTickers(market: List<String?>?) {
        CompositeDisposable().add(
            retrofit.getTicker(market)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val responseTicker = it.body()
                    if (responseTicker != null) {
                        upbitRepository.marketPriceList.set(responseTicker)
                    } else {
                        upbitRepository.throwable.set(IllegalStateException("Data is empty"))
                    }
                }, {
                    upbitRepository.throwable.set(it)
                })
        )
    }

    companion object {
        private var instance: UpbitRetrofitDataSource? = null

        operator fun invoke(): UpbitRetrofitDataSource =
            instance ?: UpbitRetrofitDataSource()
                .apply { instance = this }

    }
}