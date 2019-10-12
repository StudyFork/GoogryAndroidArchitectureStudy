package com.test.androidarchitecture.ui.market

import com.test.androidarchitecture.base.BasePresenter
import com.test.androidarchitecture.data.MarketTitle
import com.test.androidarchitecture.data.source.UpbitRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo

class MarketPresenter(
    private val view: MarketContract.View
) : MarketContract.Presenter,
    BasePresenter() {

    private val upbitRepository by lazy { UpbitRepository }

    override fun getMarketAll() {
        upbitRepository.getMarketAll()
            .map { list ->
                list.groupBy { it.market.substringBefore("-") }
                    .asSequence()
                    .map { (key, value) ->
                        MarketTitle(
                            marketTitle = key,
                            marketSearch = value.joinToString { it.market }
                        )
                    }
                    .toList()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.setViewpagerData(it)
                }, {
                    view.showToast(it.message.toString())
                }
            )
            .addTo(compositeDisposable)
    }


}