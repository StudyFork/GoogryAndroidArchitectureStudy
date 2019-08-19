package com.example.architecturestudy.ui

import android.util.Log
import com.example.architecturestudy.data.source.CoinsRepository

class MarketPresenter(
    private val marketFragmentView: MarketContract.View,
    private val repository: CoinsRepository
) : MarketContract.Presenter {

    override fun loadData(keyMarket: String?) {
        marketFragmentView.showProgressBar() // 프로그레스바 시작

        marketFragmentView.clearTickerData()

        if (keyMarket != null) {
            repository
                .getAllMarket({ coinMarketResponses ->

                    if (coinMarketResponses != null) {

                        val targetTickers = coinMarketResponses.filter {
                            it.market.split('-')[0] == keyMarket
                        }.joinToString(separator = ",") { it.market }

                        repository
                            .getCoinTickers(targetTickers, { coinTickerResponses ->
                                //map() 스트림 함수 : 컬렉션 내 인자를 변환하여 반환
                                if (coinTickerResponses != null) {
                                    marketFragmentView.showTickerData(coinTickerResponses.map { it.convertTickerIntoCoin() })
                                    marketFragmentView.hideProgressBar() // 프로그레스바 종료
                                }
                            }, { onFailCallback(it) })
                    }
                }, { onFailCallback(it) })
        }
    }

    override fun onFailCallback(errorMsg: String) {
        Log.d("test", errorMsg)
    }
}