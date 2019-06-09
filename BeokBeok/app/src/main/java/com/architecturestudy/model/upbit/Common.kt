package com.architecturestudy.model.upbit

import android.util.Log
import com.architecturestudy.model.upbit.response.Ticker
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class Common {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.upbit.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(
            OkHttpClient.Builder().addInterceptor(
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            ).build()
        )
        .build()
        .create(Retrofittable::class.java)

    fun getMarketPrice(prefix: String) {
        retrofit.getMarkets().enqueue(object : Callback<List<Ticker>> {
            override fun onResponse(call: Call<List<Ticker>>, response: Response<List<Ticker>>) {
                val markets = response.body()
                getTickers(markets?.asSequence()?.filter { prefix.length == 3 || prefix.length == 4 }
                    ?.filter { it.market!!.startsWith(prefix) }
                    ?.map { it.market }?.toList())
            }

            override fun onFailure(call: Call<List<Ticker>>, t: Throwable) {
                Log.d("kkk", "server err : " + t.message)
            }
        })
    }

    fun getTickers(market: List<String?>?) {
        retrofit.getTicker(market).enqueue(object : Callback<List<Ticker>> {
            override fun onResponse(call: Call<List<Ticker>>, response: Response<List<Ticker>>) {

            }

            override fun onFailure(call: Call<List<Ticker>>, t: Throwable) {
                Log.d("kkk", "server err : " + t.message)
            }

        })
    }

    interface Retrofittable {
        @GET("market/all")
        fun getMarkets(): Call<List<Ticker>>

        @GET("ticker")
        fun getTicker(@Query("markets") list: List<String?>?): Call<List<Ticker>>
    }
}