package com.example.architecturestudy.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.architecturestudy.data.Coin
import com.example.architecturestudy.data.source.CoinsRepositoryImpl

class MarketViewModel(
    private val repository: CoinsRepositoryImpl
) : ViewModel() {

    var coinList = MutableLiveData<List<Coin>>()
    var isProgressed = MutableLiveData<Boolean>()

    fun loadData(keyMarket: String?) {
        isProgressed.postValue(true) // 프로그래스바 시작

        if (keyMarket != null) {
            repository
                .getCoinTickers(keyMarket, { coinTickerResponses ->
                    //map() 스트림 함수 : 컬렉션 내 인자를 변환하여 반환
                    if (coinTickerResponses != null) {
                        coinList.value = (coinTickerResponses.map { it.convertTickerIntoCoin() })
                        isProgressed.value = false // 프로그래스바 종료
                    }
                }, { onFailCallback(it) })
        }
    }

    private fun onFailCallback(errorMsg: String) {
        Log.d("test", errorMsg)
    }

}