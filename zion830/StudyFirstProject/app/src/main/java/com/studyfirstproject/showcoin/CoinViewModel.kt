package com.studyfirstproject.showcoin

import android.databinding.ObservableBoolean
import android.databinding.ObservableInt
import android.util.Log
import android.view.View
import com.studyfirstproject.R
import com.studyfirstproject.adapter.CoinRecyclerViewAdapter
import com.studyfirstproject.data.CoinDataSource
import com.studyfirstproject.data.model.TickerModel

class CoinViewModel(private val repository: CoinDataSource) {

    val adapter = CoinRecyclerViewAdapter(R.layout.item_coin_info)
    val progressStatus: ObservableInt = ObservableInt(View.INVISIBLE)
    val isLoading: ObservableBoolean = ObservableBoolean(false)

    fun getMarketData() {
        progressStatus.set(View.VISIBLE)
        repository.getAllMarkets({
            repository.getCoinData(it,
                success = { tickers ->
                    onCoinsLoaded(tickers)
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

    private fun onCoinsLoaded(tickers: List<TickerModel>) {
        progressStatus.set(View.INVISIBLE)
        isLoading.set(false)
        adapter.setCoinList(tickers)
    }

    private fun onDataNotAvailable(msg: String, reason: String?) {
        Log.e(msg, reason ?: "No error message")
    }
}