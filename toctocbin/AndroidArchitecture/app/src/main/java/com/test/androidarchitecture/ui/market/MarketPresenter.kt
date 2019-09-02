package com.test.androidarchitecture.ui.market

import com.test.androidarchitecture.data.MarketTitle
import com.test.androidarchitecture.data.source.UpbitRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class MarketPresenter(private val view: MarketContract.View) : MarketContract.Presenter {

    private val upbitRepository by lazy { UpbitRepository }
    private val disposables by lazy { CompositeDisposable() }

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
            .apply { disposables.add(this) }
    }

    override fun disposablesClear() {
        disposables.clear()
    }

    companion object {

        @Volatile
        private var instance: MarketPresenter? = null

        @JvmStatic
        fun getInstance(view: MarketContract.View) = instance ?: synchronized(this) {
            instance ?: MarketPresenter(view).also {
                instance = it
            }
        }
    }
}