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
import com.aiden.aiden.architecturepatternstudy.data.model.TickerModel
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
                val modifiedMarketList = marketList.filter { item -> item.market.startsWith(market, true) } as ArrayList
                loadTickerList(modifiedMarketList)
            }

            override fun onDataNotAvailable() {
                mainView.showErrorToast()
            }

        })
    }

    override fun loadTickerList(marketList: ArrayList<MarketResponse>) {
        upbitRepository.getTickerList(marketList, object : UpbitDataSource.GetTickerListCallback {
            override fun onTickerListLoaded(tickerList: List<TickerResponse>) {
                mainView.showTickerList(modifyTickerList(tickerList))
            }

            override fun onDataNotAvailable() {
                mainView.showErrorToast()
            }

        })
    }

    private fun modifyTickerList(tickerList: List<TickerResponse>): List<TickerModel> {

        val modifiedTickerList = ArrayList<TickerModel>()

        tickerList.forEach {
            // 코인 이름
            val coinName = it.market.split("-")[1]
            //  현재 가격
            val nowPrice = if (it.market.startsWith(
                    Market.KRW.marketName,
                    true
                )
            ) {
                StringUtil.getKrwCommaPrice(BigDecimal(it.tradePrice))
            } else if (it.market.startsWith(
                    Market.BTC.marketName,
                    true
                ) || it.market.startsWith(
                    Market.ETH.marketName,
                    true
                )
            ) {
                StringUtil.getBtcEthCommaPrice(it.tradePrice)
            } else {
                StringUtil.getUsdtCommaPrice(it.tradePrice)
            }

            // 전일대비 퍼센트
            val compareBeforePercentage = StringUtil.getPercent(
                it.prevClosingPrice,
                it.tradePrice
            )

            // 거래대금
            val totalDealPrice = if (it.market.startsWith(
                    Market.KRW.marketName,
                    true
                )
            ) {
                StringUtil.getKrwTotalDealPrice(it.accTradePrice24h)
            } else if (it.market.startsWith(
                    Market.BTC.marketName,
                    true
                ) || it.market.startsWith(
                    Market.ETH.marketName,
                    true
                )
            ) {
                StringUtil.getBtcEthTotalDealPrice(it.accTradePrice24h)
            } else {
                StringUtil.getUsdtTotalDealPrice(it.accTradePrice24h)
            }
            modifiedTickerList.add(
                TickerModel(
                    coinName = coinName,
                    nowPrice = nowPrice,
                    compareBeforePercentage = compareBeforePercentage,
                    totalDealPrice = totalDealPrice,
                    prevClosingPrice = it.prevClosingPrice,
                    tradePrice = it.tradePrice
                )
            )
        }

        return modifiedTickerList

    }

}
