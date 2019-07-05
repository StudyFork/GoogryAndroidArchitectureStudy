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
package com.aiden.aiden.architecturepatternstudy.data.source

import com.aiden.aiden.architecturepatternstudy.api.model.MarketResponse
import com.aiden.aiden.architecturepatternstudy.api.model.TickerResponse

class UpbitRepository(
    private val remoteDataSource: UpbitDataSource
) : UpbitDataSource {

    override fun getMarketList(callback: UpbitDataSource.GetMarketListCallback) {
        remoteDataSource.getMarketList(object : UpbitDataSource.GetMarketListCallback {
            override fun onMarketListLoaded(marketList: List<MarketResponse>) {
                callback.onMarketListLoaded(marketList)
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }

        })
    }

    override fun getTickerList(
        marketList: List<MarketResponse>,
        callback: UpbitDataSource.GetTickerListCallback
    ) {
        remoteDataSource.getTickerList(marketList, object : UpbitDataSource.GetTickerListCallback {
            override fun onTickerListLoaded(tickerList: List<TickerResponse>) {
                callback.onTickerListLoaded(tickerList)
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }

        })
    }

    companion object {

        private var instance: UpbitRepository? = null

        operator fun invoke(UpbitRemoteDataSource: UpbitDataSource) =
            instance ?: UpbitRepository(UpbitRemoteDataSource)
                .apply { instance = this }

    }
}