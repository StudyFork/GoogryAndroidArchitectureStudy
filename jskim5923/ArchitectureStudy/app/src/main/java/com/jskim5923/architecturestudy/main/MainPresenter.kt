package com.jskim5923.architecturestudy.main

import com.jskim5923.architecturestudy.base.BasePresenter
import com.jskim5923.architecturestudy.extension.getCoinCurrency
import com.jskim5923.architecturestudy.model.data.source.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class MainPresenter(private val view: MainContract.View) : BasePresenter(), MainContract.Presenter {
    override fun loadMarketList() {
        Repository.getMarketList()
            .subscribeOn(Schedulers.io())
            .map { marketList ->
                marketList.map {
                    it.market.getCoinCurrency()
                }.distinct()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.updateViewpager(it)
            }, {
                it.printStackTrace()
            })
            .addTo(compositeDisposable)
    }
}