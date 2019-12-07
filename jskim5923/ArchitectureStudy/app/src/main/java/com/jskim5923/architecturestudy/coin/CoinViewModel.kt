package com.jskim5923.architecturestudy.coin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jskim5923.architecturestudy.base.BaseViewModel
import com.jskim5923.architecturestudy.extension.getCoinCurrency
import com.jskim5923.architecturestudy.model.Ticker
import com.jskim5923.architecturestudy.model.data.source.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class CoinViewModel(private val repository: Repository) : BaseViewModel() {
    private val _tickerList = MutableLiveData<List<Ticker>>()

    val tickerList: LiveData<List<Ticker>>
        get() = _tickerList

    fun getTickerList(market: String?) {
        repository.getMarketList()
            .subscribeOn(Schedulers.io())
            .flatMap { marketList ->
                repository.getTicker(
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
                _tickerList.value = tickerList
            }, {}
            )
            .addTo(compositeDisposable)
    }
}