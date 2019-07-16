package com.studyfirstproject.showcoin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.studyfirstproject.data.CoinDataSource
import com.studyfirstproject.data.model.TickerModel

class CoinViewModel(private val repository: CoinDataSource) {

    private val _isLoading by lazy { MutableLiveData<Boolean>() }
    private val _errorMsg by lazy { MutableLiveData<String>() }
    private val _items = MutableLiveData<List<TickerModel>>()

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    val errorMsg: LiveData<String>
        get() = _errorMsg

    val items: LiveData<List<TickerModel>>
        get() = _items

    fun init() {
        _items.value = mutableListOf()
        _isLoading.value = true
        getMarketData()
    }

    private fun getMarketData() {
        repository.getAllMarkets({
            repository.getCoinData(it,
                success = { tickers ->
                    _isLoading.value = false
                    _items.value = tickers
                }, failed = { msg, reason ->
                    onDataNotAvailable(msg, reason)
                })
        }, { msg, reason ->
            onDataNotAvailable(msg, reason)
        })
    }

    fun onRefresh() {
        _isLoading.value = true
        getMarketData()
    }

    private fun onDataNotAvailable(msg: String, reason: String?) {
        Log.e(msg, reason ?: "No error message")
        _errorMsg.value = "오류가 발생했습니다. 다시 시도해주세요."
        _isLoading.value = false
    }
}