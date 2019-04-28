package my.gong.studygong.data.network

import my.gong.studygong.data.model.response.UpbitMarketResponse
import my.gong.studygong.data.model.response.UpbitTickerResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query

interface UpbitApi {
    companion object {
        val BASE_URL = " https://api.upbit.com/"
    }
    @GET("v1/market/all")
    fun getMarket(): Call<List<UpbitMarketResponse>>

    @GET("v1/ticker")
    fun getTicker(@Query("markets") tikers: String): Call<List<UpbitTickerResponse>>
}