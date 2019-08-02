package com.example.architecturestudy.ui

import android.graphics.Color
import android.util.Log
import com.example.architecturestudy.data.Coin
import com.example.architecturestudy.data.CoinTickerResponse
import com.example.architecturestudy.data.source.CoinsRepository
import com.example.architecturestudy.util.Util

class MarketPresenter(
    private val marketFragmentView: MarketContract.View,
    private val repository: CoinsRepository
) : MarketContract.Presenter {


    override fun loadData(keyMarket: String) {
        marketFragmentView.clearTickerData()

        repository
            .getAllMarket({ coinMarketResponse ->

                if (coinMarketResponse != null) {

                    val targetTickers = coinMarketResponse.filter {
                        it.market.split('-')[0] == keyMarket
                    }.joinToString(separator = ",") { it.market }

                    repository
                        .getCoinTickers(targetTickers, { coinTickerResponse ->
                            //map() 스트림 함수 : 컬렉션 내 인자를 변환하여 반환
                            if (coinTickerResponse != null) {
                                marketFragmentView.setTickerData(coinTickerResponse.map(::convertTickerIntoCoin))
                            }
                        }, { onFailCallback(it) })
                }
            }, { onFailCallback(it) })
    }

    override fun onFailCallback(errorMsg: String) {
        Log.d("test", errorMsg)
    }

    override fun convertTickerIntoCoin(ticker: CoinTickerResponse): Coin {
        //코인 이름
        var market = ticker.market.split("-")[1]

        //현재가
        var tradePrice = when {
            ticker.tradePrice > 1_000 ->
                Util.convertBigNumberToStdString(ticker.tradePrice.toInt())
            ticker.tradePrice > 2 ->
                String.format("%.2f", ticker.tradePrice)
            else ->
                String.format("%.8f", ticker.tradePrice)
        }

        //전일대비
        var signedChangeRate = String.format("%.2f", ticker.signedChangeRate * 100) + "%"

        //전일대비 색깔 지정
        val coinColor = if (signedChangeRate.startsWith('-')) Color.BLUE else Color.RED

        //거래대금
        var accTradePriceH = when {
            ticker.accTradePriceH > 10_000_000 -> {
                Util.convertBigNumberToStdString((ticker.accTradePriceH / 1000000).toInt()) + "M"
            }

            ticker.accTradePriceH > 100_000 ->
                Util.convertBigNumberToStdString(ticker.accTradePriceH.toInt() / 1000) + "k"

            ticker.accTradePriceH > 1_000 ->
                Util.convertBigNumberToStdString(ticker.accTradePriceH.toInt())

            else ->
                String.format("%.3f", ticker.accTradePriceH)
        }

        return Coin(market, tradePrice, signedChangeRate, accTradePriceH, coinColor)
    }

}