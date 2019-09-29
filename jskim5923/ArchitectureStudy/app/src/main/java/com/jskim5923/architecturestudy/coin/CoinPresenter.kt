package com.jskim5923.architecturestudy.coin

import com.jskim5923.architecturestudy.extension.getCoinCurrency
import com.jskim5923.architecturestudy.model.data.source.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers

class CoinPresenter(override val view: CoinContract.View) : CoinContract.Presenter {
    override val compositeDisposable = CompositeDisposable()

    override fun getTickerList(market: String?) {
        compositeDisposable += Repository.getMarketList()
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
                view.updateRecyclerView(tickerList)
            }, { e ->
                e.printStackTrace()
            })
    }

    override fun clearCompositeDisposable() {
        compositeDisposable.clear()
    }
}