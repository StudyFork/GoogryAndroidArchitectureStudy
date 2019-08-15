package com.jake.archstudy.data.source

import com.jake.archstudy.network.response.MarketResponse
import com.jake.archstudy.network.response.TickerResponse
import retrofit2.Call

interface UpbitDataSource {

    fun getMarketAll(): Call<List<MarketResponse>>

    fun getTicker(markets: String): Call<List<TickerResponse>>

}