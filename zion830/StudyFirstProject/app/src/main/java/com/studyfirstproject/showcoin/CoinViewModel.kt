package com.studyfirstproject.showcoin

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.util.Log
import android.view.View
import com.studyfirstproject.data.CoinDataSource
import com.studyfirstproject.data.model.TickerModel

class CoinViewModel(private val repository: CoinDataSource) {

    val progressStatus: ObservableInt = ObservableInt(View.INVISIBLE)
    val isLoading: ObservableBoolean = ObservableBoolean(false)
    val items = ObservableField<List<TickerModel>>(listOf())

    fun getMarketData() {
        progressStatus.set(View.VISIBLE)
        repository.getAllMarkets({
            repository.getCoinData(it,
                success = { tickers ->
                    progressStatus.set(View.INVISIBLE)
                    isLoading.set(false)
                    items.set(tickers)
                }, failed = { msg, reason ->
                    onDataNotAvailable(msg, reason)
                })
        }, { msg, reason ->
            onDataNotAvailable(msg, reason)
        })
    }

    fun onRefresh() {
        isLoading.set(true)
        getMarketData()
    }

    private fun onDataNotAvailable(msg: String, reason: String?) {
        Log.e(msg, reason ?: "No error message")
    }
}