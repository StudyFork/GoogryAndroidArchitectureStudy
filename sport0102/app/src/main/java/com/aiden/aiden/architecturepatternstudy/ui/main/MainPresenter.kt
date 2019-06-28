/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aiden.aiden.architecturepatternstudy.ui.main

import com.aiden.aiden.architecturepatternstudy.api.model.MarketResponse
import com.aiden.aiden.architecturepatternstudy.api.model.TickerResponse
import com.aiden.aiden.architecturepatternstudy.data.enums.Market
import com.aiden.aiden.architecturepatternstudy.data.source.UpbitDataSource
import com.aiden.aiden.architecturepatternstudy.data.source.UpbitRepository
import com.aiden.aiden.architecturepatternstudy.util.StringUtil
import java.math.BigDecimal

/**
 * Listens to user actions from the UI ([TasksFragment]), retrieves the data and updates the
 * UI as required.
 */
class MainPresenter(
    private val upbitRepository: UpbitRepository,
    val mainView: MainContract.View
) :
    MainContract.Presenter {


    init {
        mainView.presenter = this
    }

    override fun loadMarketList(market: String) {
        upbitRepository.getMarketList(object : UpbitDataSource.GetMarketListCallback {
            override fun onMarketListLoaded(marketList: List<MarketResponse>) {
                val modifiedMarketList = marketList.filter { item -> item.market.startsWith(market, true) }
                loadTickerList(modifiedMarketList)
            }

            override fun onDataNotAvailable() {
                mainView.showErrorToast()
            }

        })
    }

    override fun loadTickerList(marketList: List<MarketResponse>) {
        upbitRepository.getTickerList(marketList, object : UpbitDataSource.GetTickerListCallback {
            override fun onTickerListLoaded(tickerList: List<TickerResponse>) {
                mainView.showTickerList(tickerList.map(::modifyTicker))
            }

            override fun onDataNotAvailable() {
                mainView.showErrorToast()
            }

        })
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
