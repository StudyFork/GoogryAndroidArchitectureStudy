package com.test.androidarchitecture.network

import com.test.androidarchitecture.data.Market
import io.reactivex.Single
import retrofit2.http.GET
import kotlin.collections.ArrayList

interface RetrofitService {

    @GET("market/all")
    fun loadMarketData() : Single<ArrayList<Market>>
}