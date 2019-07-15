package study.architecture.myarchitecture.network.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import study.architecture.myarchitecture.network.model.UpbitMarket
import study.architecture.myarchitecture.network.model.UpbitTicker

interface UpbitApi {

    @GET(value = "v1/market/all")
    fun getMarkets(): Single<List<UpbitMarket>>

    @GET(value = "v1/ticker")
    fun getTickers(@Query("markets") markets: String): Single<List<UpbitTicker>>
}