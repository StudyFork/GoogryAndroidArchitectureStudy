package com.example.seonoh.seonohapp.model

import android.util.Log
import androidx.databinding.ObservableField
import com.example.seonoh.seonohapp.repository.CoinRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(repo : CoinRepositoryImpl) : BaseViewModel() {
    val marketInfo = ObservableField<List<String>>()
    private val mainRepository = repo

    init {
        loadData()
    }

    private fun loadData() {
        add(
            mainRepository.sendMarket()
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