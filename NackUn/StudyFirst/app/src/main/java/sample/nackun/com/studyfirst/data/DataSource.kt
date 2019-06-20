package sample.nackun.com.studyfirst.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import sample.nackun.com.studyfirst.vo.Market
import sample.nackun.com.studyfirst.vo.Ticker

interface DataSource {
    interface UpbitApi {
        @GET("market/all")
        fun requestMarket(): Call<ArrayList<Market>>

        @GET("ticker/")
        fun requestTicker(@Query("markets") markets: String): Call<ArrayList<Ticker>>
    }

    interface RequestTickersCallback {
        fun onTickersLoaded(tickers: ArrayList<Ticker>)

        fun onError(err: String?)
    }

    fun requestMarkets(marketLike: String, callback: RequestTickersCallback)
}