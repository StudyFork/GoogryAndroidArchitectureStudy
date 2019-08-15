package com.jake.archstudy.data.source

import com.jake.archstudy.network.ApiUtil
import com.jake.archstudy.network.response.MarketResponse
import com.jake.archstudy.network.service.UpbitService
import retrofit2.Call
import retrofit2.create

class UpbitRepository : UpbitDataSource {

    private val service = ApiUtil.getRetrofit().create<UpbitService>()

    override fun getMarketAll(): Call<List<MarketResponse>> {
        return service.getMarketAll()
    }

}