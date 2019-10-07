package com.example.architecturestudy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.architecturestudy.data.Coin
import com.example.architecturestudy.data.source.CoinsRepositoryImpl

class MarketViewModel(
    private val repository: CoinsRepositoryImpl
) : ViewModel() {
    val coinList = MutableLiveData<List<Coin>>()
    val isProgressed = MutableLiveData<Boolean>()
    val notificationMsg = MutableLiveData<String>()

    fun loadData(keyMarket: String?) {
        if (keyMarket != null) {
            isProgressed.value = true // 프로그래스바 시작

            repository
                .getCoinTickers(keyMarket, { coinTickerResponses ->
                    //map() 스트림 함수 : 컬렉션 내 인자를 변환하여 반환
                    if (!coinTickerResponses.isNullOrEmpty()) {
                        coinList.value = coinTickerResponses.map { it.convertTickerIntoCoin() }
                    }
                    isProgressed.value = false // 프로그래스바 종료
                }, {
                    isProgressed.value = false // 프로그래스바 종료
                    onFailCallback(it)
                })
        } else {
            onFailCallback("데이터를 불러올 수 없습니다")
        }
    }

    private fun onFailCallback(errorMsg: String) {
        notificationMsg.value = errorMsg
    }

}