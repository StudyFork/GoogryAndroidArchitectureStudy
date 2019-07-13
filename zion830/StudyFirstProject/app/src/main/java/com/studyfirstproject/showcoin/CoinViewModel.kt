package com.studyfirstproject.showcoin

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.util.Log
import com.studyfirstproject.data.CoinDataSource
import com.studyfirstproject.data.model.TickerModel

class CoinViewModel(private val repository: CoinDataSource) {

    val isLoading: ObservableBoolean = ObservableBoolean(false)
    val items = ObservableField<List<TickerModel>>(listOf())

    fun getMarketData() {
        repository.getAllMarkets({
            repository.getCoinData(it,
                success = { tickers ->
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