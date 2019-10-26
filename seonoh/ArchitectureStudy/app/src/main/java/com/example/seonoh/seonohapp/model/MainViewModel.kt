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
            .addCompositeDisposable()

    }

    private fun refineData(marketData: List<Market>): List<String> {

        val marketDataList = ArrayList<String>()

        marketData
            .groupBy { it.market.substringBefore("-") }
            .map {
                val market = it.value.joinToString { it.market }
                marketDataList += market
            }


        return marketDataList
    }


}