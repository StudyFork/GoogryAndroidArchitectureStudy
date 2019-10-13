package com.jskim5923.architecturestudy.coin

import androidx.databinding.ObservableField
import com.jskim5923.architecturestudy.base.BaseViewModel
import com.jskim5923.architecturestudy.extension.getCoinCurrency
import com.jskim5923.architecturestudy.model.Ticker
import com.jskim5923.architecturestudy.model.data.source.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class CoinViewModel : BaseViewModel() {
    val observableTickerList = ObservableField<List<Ticker>>(mutableListOf())

    fun getTickerList(market: String?) {
        Repository.getMarketList()
            .subscribeOn(Schedulers.io())
            .flatMap { marketList ->
                Repository.getTicker(
                    marketList.asSequence()
                        .filter {
                            it.market.getCoinCurrency() == market
                        }.toList().joinToString(",") {
                            it.market
                        }
                )
            }
            .flattenAsObservable { tickerResponseList ->
                tickerResponseList.asSequence()
                    .map { tickerResponse ->
                        tickerResponse.toTicker()
                    }.toList()
            }
            .toList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ tickerList ->
                observableTickerList.set(tickerList)
            }, { e ->
                e.printStackTrace()
            })
            .addTo(compositeDisposable)
    }
}