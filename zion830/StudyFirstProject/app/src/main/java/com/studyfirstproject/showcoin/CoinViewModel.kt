package com.studyfirstproject.showcoin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.studyfirstproject.data.CoinRepository
import com.studyfirstproject.data.model.TickerModel

class CoinViewModel(private val repository: CoinRepository) : ViewModel() {

    private var isFirstLoading = true
    private val _isLoading = MutableLiveData<Boolean>()
    private val _isNoData = MutableLiveData<Boolean>()
    private val _errorMsg = MutableLiveData<String>()
    private val _items = MutableLiveData<List<TickerModel>>()

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    val isNoData: LiveData<Boolean>
        get() = _isNoData

    val errorMsg: LiveData<String>
        get() = _errorMsg

    val items: LiveData<List<TickerModel>>
        get() = _items

    fun init() {
        _items.value = mutableListOf()
        getMarketData()
    }

    private fun getMarketData() {
        _isLoading.value = true

        repository.getAllMarkets({
            repository.getCoinData(it,
                success = { tickers ->
                    _isLoading.value = false
                    _items.value = tickers
                    _isNoData.value = false

                    if (isFirstLoading) {
                        isFirstLoading = false
                    }
                }, failed = { msg, reason ->
                    onDataNotAvailable(msg, reason)
                })
        }, { msg, reason ->
            onDataNotAvailable(msg, reason)
        })
    }

    fun onRefresh() {
        getMarketData()
    }

    private fun onDataNotAvailable(msg: String, reason: String?) {
        Log.e(msg, reason ?: "No error message")
        _errorMsg.value = "네트워크 오류가 발생했습니다."
        _isLoading.value = false

        if (isFirstLoading) {
            _isNoData.value = true
            isFirstLoading = false
        }
    }
}