package com.architecturestudy.model.upbit

import android.util.Log
import com.architecturestudy.model.upbit.response.MarketAll
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

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

    fun getMarketList(prefix: String) {
        retrofit.getMarketAll().enqueue(object : Callback<List<MarketAll>> {
            override fun onResponse(call: Call<List<MarketAll>>, response: Response<List<MarketAll>>) {
                val marketAll = response.body()
                // TODO 필터된 리스트를 RecylcerView 에 랜더링 작업
                val filteredList =
                    marketAll?.asSequence()?.filter { prefix.length == 3 || prefix.length == 4 }
                        ?.filter { it.market!!.startsWith(prefix) }
                        ?.map { it.market?.substringAfter("-") }?.toList()
            }

            override fun onFailure(call: Call<List<MarketAll>>, t: Throwable) {
                Log.d("kkk", "server err : " + t.message)
            }
        })
    }

    interface Retrofittable {
        @GET("market/all")
        fun getMarketAll(): Call<List<MarketAll>>
    }
}