package com.jake.archstudy.data.source

import com.jake.archstudy.network.response.MarketResponse
import com.jake.archstudy.network.response.TickerResponse
import com.jake.archstudy.network.service.UpbitService
import retrofit2.Call

class UpbitRepository(private val service: UpbitService) : UpbitDataSource {

    override fun getMarketAll(): Call<List<MarketResponse>> {
        return service.getMarketAll()
    }

    override fun getTicker(markets: String): Call<List<TickerResponse>> {
        return service.getTicker(markets)
    }

}