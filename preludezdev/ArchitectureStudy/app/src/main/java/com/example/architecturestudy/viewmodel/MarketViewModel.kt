package com.example.architecturestudy.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.architecturestudy.data.Coin
import com.example.architecturestudy.data.source.CoinsRepositoryImpl

class MarketViewModel(
    private val repository: CoinsRepositoryImpl
) : ViewModel() {
    val coinList = ObservableField<List<Coin>>()
    val isProgressed = ObservableField<Boolean>()
    val notificationMsg = ObservableField<String>()

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
                    isProgressed.set(false) // 프로그래스바 종료
                    onFailCallback(it)
                })
        } else {
            onFailCallback("데이터를 불러올 수 없습니다")
        }
    }

    private fun onFailCallback(errorMsg: String) {
        notificationMsg.set(errorMsg)
    }

}