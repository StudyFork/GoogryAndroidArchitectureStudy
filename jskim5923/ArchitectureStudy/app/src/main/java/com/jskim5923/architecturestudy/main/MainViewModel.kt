package com.jskim5923.architecturestudy.main

import androidx.databinding.ObservableField
import com.jskim5923.architecturestudy.base.BaseViewModel
import com.jskim5923.architecturestudy.extension.getCoinCurrency
import com.jskim5923.architecturestudy.model.data.source.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class MainViewModel : BaseViewModel() {
    val marketList = ObservableField<List<String>>(mutableListOf())

    init {
        loadMarketList()
    }

    private fun loadMarketList() {
        Repository.getMarketList()
            .subscribeOn(Schedulers.io())
            .map { marketList ->
                marketList.map {
                    it.market.getCoinCurrency()
                }.distinct()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                marketList.set(it)
            }, {
                it.printStackTrace()
            })
            .addTo(compositeDisposable)
    }
}