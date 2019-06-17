package dev.daeyeon.gaasproject.network

import dev.daeyeon.gaasproject.data.response.MarketResponse
import dev.daeyeon.gaasproject.data.response.TickerResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface UpbitApi {

    @GET("market/all")
    fun getMarketCode(): Single<List<MarketResponse>>

    @GET("ticker")
    fun getTicker(@Query("markets") markets: String): Observable<List<TickerResponse>>
}