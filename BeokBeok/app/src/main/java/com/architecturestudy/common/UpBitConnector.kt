package com.architecturestudy.common

import android.util.Log
import com.architecturestudy.model.UpBitTickerResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class UpBitConnector(val receiver: UpBitCommunicable) {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.upbit.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(
            OkHttpClient.Builder().addInterceptor(
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            ).build()
        )
        .build()
        .create(UpBitService::class.java)

    fun getMarketPrice(prefix: String) {
        retrofit.getMarkets().enqueue(object : Callback<List<UpBitTickerResponse>> {
            override fun onResponse(
                call: Call<List<UpBitTickerResponse>>,
                response: Response<List<UpBitTickerResponse>>
            ) {
                val markets = response.body() ?: return
                val tickers = markets
                    .asSequence()
                    .filter { hasPrefix(prefix) }
                    .filter { it.market!!.startsWith(prefix) }
                    .map { it.market }
                    .toList()
                getTickers(tickers)
            }

            override fun onFailure(call: Call<List<UpBitTickerResponse>>, t: Throwable) {
                Log.d("kkk", "server err : " + t.message)
            }
        })
    }

    fun getTickers(market: List<String?>?) {
        retrofit.getTicker(market).enqueue(object : Callback<List<UpBitTickerResponse>> {
            override fun onResponse(
                call: Call<List<UpBitTickerResponse>>,
                response: Response<List<UpBitTickerResponse>>
            ) {
                val responseTicker = response.body()
                responseTicker?.let { receiver.onSuccessMarketPrice(it) }
            }

            override fun onFailure(call: Call<List<UpBitTickerResponse>>, t: Throwable) {
                Log.d("kkk", "server err : " + t.message)
            }

        })
    }

    fun hasPrefix(prefix: String): Boolean {
        return listOf("KRW", "BTC", "ETH", "USDT").contains(prefix)
    }

    interface UpBitService {
        @GET("market/all")
        fun getMarkets(): Call<List<UpBitTickerResponse>>

        @GET("ticker")
        fun getTicker(@Query("markets") list: List<String?>?): Call<List<UpBitTickerResponse>>
    }
}