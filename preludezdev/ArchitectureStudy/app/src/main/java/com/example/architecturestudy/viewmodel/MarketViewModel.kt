package com.example.architecturestudy.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import com.example.architecturestudy.data.Coin
import com.example.architecturestudy.data.source.CoinsRepositoryImpl

class MarketViewModel(
    private val repository: CoinsRepositoryImpl
) {
    val coinList = ObservableField<List<Coin>>()
    val isProgressed = ObservableField<Boolean>()

    fun loadData(keyMarket: String?) {
        if (keyMarket != null) {
            isProgressed.set(true) // 프로그래스바 시작

            repository
                .getCoinTickers(keyMarket, { coinTickerResponses ->
                    //map() 스트림 함수 : 컬렉션 내 인자를 변환하여 반환
                    if (!coinTickerResponses.isNullOrEmpty()) {
                        coinList.set((coinTickerResponses.map { it.convertTickerIntoCoin() }))
                    }
                    isProgressed.set(false) // 프로그래스바 종료
                }, {
                    onFailCallback(it)
                    isProgressed.set(false) // 프로그래스바 종료
                })

        }
    }

    private fun onFailCallback(errorMsg: String) {
        Log.d("test", errorMsg)
    }

}