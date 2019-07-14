package com.aiden.aiden.architecturepatternstudy.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aiden.aiden.architecturepatternstudy.api.model.MarketResponse
import com.aiden.aiden.architecturepatternstudy.api.model.TickerResponse
import com.aiden.aiden.architecturepatternstudy.data.enums.Market
import com.aiden.aiden.architecturepatternstudy.data.source.UpbitRepository
import com.aiden.aiden.architecturepatternstudy.util.StringUtil
import java.math.BigDecimal

class MainViewModel(private val upbitRepository: UpbitRepository) {

    private var _tickerList = MutableLiveData<List<TickerResponse>>()
    val tickerList: LiveData<List<TickerResponse>> get() = _tickerList

    private var _isDataLoadingError = MutableLiveData<Boolean>()
    val isDataLoadingError: LiveData<Boolean> get() = _isDataLoadingError

    fun loadMarketList(market: String) {

        upbitRepository.getMarketList(onSuccess = {
            val modifiedMarketList =
                it.filter { item -> item.market.startsWith(market, true) }
            loadTickerList(modifiedMarketList)
        },

            onFail = {
                _isDataLoadingError.value = true
            }

        )

    }

    private fun loadTickerList(marketList: List<MarketResponse>) {

        upbitRepository.getTickerList(marketList, onSuccess = {
            _tickerList.value = it.map(::modifyTicker)
        },

            onFail = {
                _isDataLoadingError.value = true
            }
        
        )

    }

    private fun modifyTicker(ticker: TickerResponse): TickerResponse {

        with(ticker) {

            // 코인 이름
            coinName = market.split("-")[1]

            //  현재 가격
            nowPrice = if (market.startsWith(
                    Market.KRW.marketName,
                    true
                )
            ) {
                StringUtil.getKrwCommaPrice(BigDecimal(tradePrice))
            } else if (market.startsWith(
                    Market.BTC.marketName,
                    true
                ) || market.startsWith(
                    Market.ETH.marketName,
                    true
                )
            ) {
                StringUtil.getBtcEthCommaPrice(tradePrice)
            } else {
                StringUtil.getUsdtCommaPrice(tradePrice)
            }

            // 전일대비 퍼센트
            compareBeforePercentage = StringUtil.getPercent(
                prevClosingPrice,
                tradePrice
            )

            // 거래대금
            totalDealPrice = if (market.startsWith(
                    Market.KRW.marketName,
                    true
                )
            ) {
                StringUtil.getKrwTotalDealPrice(accTradePrice24h)
            } else if (market.startsWith(
                    Market.BTC.marketName,
                    true
                ) || market.startsWith(
                    Market.ETH.marketName,
                    true
                )
            ) {
                StringUtil.getBtcEthTotalDealPrice(accTradePrice24h)
            } else {
                StringUtil.getUsdtTotalDealPrice(accTradePrice24h)
            }

        }

        return ticker

    }
}
