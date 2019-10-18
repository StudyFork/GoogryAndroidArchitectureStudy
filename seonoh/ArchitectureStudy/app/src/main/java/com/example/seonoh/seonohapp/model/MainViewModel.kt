package com.example.seonoh.seonohapp.model

import android.util.Log
import androidx.databinding.ObservableField
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel : BaseViewModel() {
    var marketInfo = ObservableField<List<String>>()

    init {
        loadData()
    }

    private fun loadData() {
        compositeDisposable.add(
            coinRepository.sendMarket()
                .map {
                    refineData(it)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    marketInfo.set(it)
                }, {
                    Log.e("currentPriceInfo", "Main Network failed!! ${it.message}")
                })
        )
    }

    private fun refineData(marketData: List<Market>): List<String> {

        var coinMarketNameList = marketData.map {
            it.market.substringBefore("-")
        }.distinct()

        val marketDataList = ArrayList<String>()

        coinMarketNameList.forEach { title ->

            marketDataList += (marketData.filter {
                it.market.substringBefore("-") == title
            }.joinToString(",") {
                it.market
            })
        }

        return marketDataList
    }


}