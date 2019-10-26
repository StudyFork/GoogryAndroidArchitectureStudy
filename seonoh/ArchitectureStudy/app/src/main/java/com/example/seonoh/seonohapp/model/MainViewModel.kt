package com.example.seonoh.seonohapp.model

import android.util.Log
import androidx.databinding.ObservableField
import com.example.seonoh.seonohapp.repository.CoinRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val repo: CoinRepositoryImpl) : BaseViewModel() {
    val marketInfo = ObservableField<List<String>>()

    init {
        loadData()
    }

    private fun handleError(throwable: Throwable) {
        Log.e("currentPriceInfo", "Main Network failed!! ${throwable.message}")
    }

    private fun loadData() {

        repo.sendMarket()
            .map(::refineData)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(marketInfo::set, ::handleError)
            .add()

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