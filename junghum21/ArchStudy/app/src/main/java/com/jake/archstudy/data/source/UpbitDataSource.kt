package com.jake.archstudy.data.source

import com.jake.archstudy.network.response.MarketResponse
import retrofit2.Call

interface UpbitDataSource {

    fun getMarketAll(): Call<List<MarketResponse>>

}