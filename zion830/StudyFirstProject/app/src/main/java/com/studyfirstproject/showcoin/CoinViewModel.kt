package com.studyfirstproject.showcoin

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.util.Log.e
import android.view.View
import com.studyfirstproject.R
import com.studyfirstproject.adapter.CoinRecyclerViewAdapter
import com.studyfirstproject.data.CoinDataSource
import com.studyfirstproject.data.model.TickerModel

class CoinViewModel(
    private val repository: CoinDataSource
) : ViewModel(),
    CoinDataSource.LoadMarketsCallback,
    CoinDataSource.LoadTickersCallback {

    val items = ObservableField<List<TickerModel>>()
    val adapter = CoinRecyclerViewAdapter(R.layout.item_coin_info)
    val progressStatus: ObservableInt = ObservableInt(View.INVISIBLE)
    val isLoading: ObservableBoolean = ObservableBoolean(false)

    fun getMarketData() {
        progressStatus.set(View.VISIBLE)
        repository.getAllMarkets(this)
    }

    fun onRefresh() {
        isLoading.set(true)
        getMarketData()
    }

    override fun onMarketsLoaded(markets: String) {
        repository.getCoinData(markets, this)
    }

    override fun onCoinsLoaded(tickers: List<TickerModel>) {
        progressStatus.set(View.INVISIBLE)
        isLoading.set(false)
        adapter.setCoinList(tickers)
        adapter.notifyDataSetChanged()
    }

    override fun onDataNotAvailable(msg: String, reason: String?) {
        e(javaClass.toString(), reason ?: "No error message")
    }
}