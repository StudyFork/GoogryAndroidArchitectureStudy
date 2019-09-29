package com.jskim5923.architecturestudy.main

import com.jskim5923.architecturestudy.extension.getCoinCurrency
import com.jskim5923.architecturestudy.model.data.source.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers

class MainPresenter(override val view: MainContract.View) : MainContract.Presenter {
    override val compositeDisposable = CompositeDisposable()

    override fun loadMarketList() {
        compositeDisposable += Repository.getMarketList()
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
    }

    override fun clearCompositeDisposable() {
        compositeDisposable.clear()
    }

}