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
            showProgressBar(true) // 프로그래스바 종료 // 프로그래스바 시작

            repository
                .getCoinTickers(keyMarket, { coinTickerResponses ->
                    //map() 스트림 함수 : 컬렉션 내 인자를 변환하여 반환
                    if (!coinTickerResponses.isNullOrEmpty()) {
                        _coinList.value = coinTickerResponses.map { it.convertTickerIntoCoin() }
                    }

                    showProgressBar(false) // 프로그래스바 종료
                }, {
                    showProgressBar(false) // 프로그래스바 종료
                    onFailCallback(it)
                })
        } else {
            onFailCallback("데이터를 불러올 수 없습니다")
        }
    }

    private fun onFailCallback(errorMsg: String) {
        _notificationMsg.value = errorMsg
    }

    private fun showProgressBar(status: Boolean) {
        _isProgressed.value = status
    }

}