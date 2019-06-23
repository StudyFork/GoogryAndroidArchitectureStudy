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

import com.aiden.aiden.architecturepatternstudy.api.model.MarketModel
import com.aiden.aiden.architecturepatternstudy.api.model.TickerModel
import com.aiden.aiden.architecturepatternstudy.data.source.UpbitDataSource
import com.aiden.aiden.architecturepatternstudy.data.source.UpbitRepository

/**
 * Listens to user actions from the UI ([TasksFragment]), retrieves the data and updates the
 * UI as required.
 */
class MainPresenter(private val upbitRepository: UpbitRepository, val mainView: MainContract.View) :
    MainContract.Presenter {

    override fun start() {
        //no-op
    }

    init {
        mainView.presenter = this
    }

    override fun loadMarketList(market: String) {
        upbitRepository.getMarketList(object : UpbitDataSource.GetMarketListCallback {
            override fun onMarketListLoaded(marketList: List<MarketModel>) {
                val modifiedMarketList = marketList.filter { item -> item.market.startsWith(market, true) } as ArrayList
                loadTickerList(modifiedMarketList)
            }

            override fun onDataNotAvailable() {
                mainView.showErrorToast()
            }

        })
    }

    override fun loadTickerList(marketList: ArrayList<MarketModel>) {
        upbitRepository.getTickerList(marketList, object : UpbitDataSource.GetTickerListCallback {
            override fun onTickerListLoaded(tickerList: List<TickerModel>) {
                mainView.showTickerList(tickerList)
            }

            override fun onDataNotAvailable() {
                mainView.showErrorToast()
            }

        })
    }
}
