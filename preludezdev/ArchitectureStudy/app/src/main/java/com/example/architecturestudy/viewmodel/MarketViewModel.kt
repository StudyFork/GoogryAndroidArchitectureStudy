package com.example.architecturestudy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.architecturestudy.data.Coin
import com.example.architecturestudy.data.source.CoinsRepositoryImpl

class MarketViewModel(
    private val repository: CoinsRepositoryImpl
) : ViewModel() {

    private val _coinList = MutableLiveData<List<Coin>>()
    val coinList: LiveData<List<Coin>> get() = _coinList

    private val _isProgressed = MutableLiveData<Boolean>()
    val isProgressed: LiveData<Boolean> get() = _isProgressed

    private val _notificationMsg = MutableLiveData<String>()
    val notificationMsg: LiveData<String> get() = _notificationMsg

    fun loadData(keyMarket: String?) {
        if (keyMarket != null) {
            showProgressBar()

            repository
                .getCoinTickers(keyMarket, { coinTickerResponses ->
                    if (!coinTickerResponses.isNullOrEmpty()) {
                        _coinList.value = coinTickerResponses.map { it.convertTickerIntoCoin() }
                    }

                    hideProgressBar()
                }, {
                    hideProgressBar()
                    showToastErrorMessage(it)
                })
        } else {
            showToastErrorMessage("데이터를 불러올 수 없습니다 (데이터 요청 키가 없습니다.)")
        }
    }

    private fun showToastErrorMessage(errorMsg: String) {
        _notificationMsg.value = errorMsg
    }

    private fun showProgressBar() {
        _isProgressed.value = true
    }

    private fun hideProgressBar() {
        _isProgressed.value = false
    }

}