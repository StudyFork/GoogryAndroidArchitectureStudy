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
package com.aiden.aiden.architecturepatternstudy.data.source.remote

import com.aiden.aiden.architecturepatternstudy.api.UpbitApi
import com.aiden.aiden.architecturepatternstudy.api.model.MarketModel
import com.aiden.aiden.architecturepatternstudy.api.model.TickerModel
import com.aiden.aiden.architecturepatternstudy.api.retrofit
import com.aiden.aiden.architecturepatternstudy.data.source.UpbitDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Implementation of the data source that adds a latency simulating network.
 */
object UpbitRemoteDataSource : UpbitDataSource {

    private val retrofitService: UpbitApi = retrofit.create(UpbitApi::class.java)

    override fun getMarketList(callback: UpbitDataSource.GetMarketListCallback) {
        retrofitService.getMarketList().enqueue(object : Callback<ArrayList<MarketModel>> {

            override fun onFailure(call: Call<ArrayList<MarketModel>>?, t: Throwable?) {
                callback.onDataNotAvailable()
            }

            override fun onResponse(call: Call<ArrayList<MarketModel>>?, response: Response<ArrayList<MarketModel>>?) {
                response?.body()?.let {
                    callback.onMarketListLoaded(it)
                }
            }
        })
    }

    override fun getTickerList(marketList: ArrayList<MarketModel>, callback: UpbitDataSource.GetTickerListCallback) {
        retrofitService.getTickerInfo(marketList.joinToString { marketModel -> marketModel.market })
            .enqueue(object : Callback<ArrayList<TickerModel>> {
                override fun onFailure(call: Call<ArrayList<TickerModel>>, t: Throwable) {
                    callback.onDataNotAvailable()
                }

                override fun onResponse(
                    call: Call<ArrayList<TickerModel>>,
                    response: Response<ArrayList<TickerModel>>
                ) {
                    response.body()?.let {
                        callback.onTickerListLoaded(it)
                    }
                }

            })
    }

}