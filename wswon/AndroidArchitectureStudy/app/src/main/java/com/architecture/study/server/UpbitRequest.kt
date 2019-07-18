package com.architecture.study.server

import com.architecture.study.model.Market
import com.architecture.study.model.Ticker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


class UpbitRequest {
    val URL = "https://api.upbit.com"
    lateinit var mListener: UpbitRequestListener

    interface UpbitRequestListener {
        fun onSucess(dataList: List<Any>)
        fun onEmpty(str: String)
        fun onFailure(str: String)
    }

    private interface ApiService {
        @GET("/v1/market/all")
        fun getMarketData(): Call<List<Market>>

        @GET("/v1/ticker?")
        fun getTickerData(
            @Query("markets") markets: String
        ): Call<List<Ticker>>
    }

    fun setOnUpbitRequestListener(listener: UpbitRequestListener) {
        this.mListener = listener
    }


    fun getMarketList() {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(ApiService::class.java)

        apiService.getMarketData().enqueue(object : Callback<List<Market>> {
            override fun onResponse(call: Call<List<Market>>, response: Response<List<Market>>) {
                if (response.isSuccessful) {
                    val marketList = response.body()
                    if (marketList != null) {
                        mListener.onSucess(marketList)
                    } else {
                        mListener.onEmpty("data empty")
                    }
                }
            }

            override fun onFailure(call: Call<List<Market>>, t: Throwable) {
                t.message?.let {
                    mListener.onFailure(it)
                }
            }
        })
    }

    fun getTickerList(marketNames: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(UpbitRequest.ApiService::class.java)


        val call = apiService.getTickerData(marketNames)

        call.enqueue(object : Callback<List<Ticker>> {
            override fun onResponse(call: Call<List<Ticker>>, response: Response<List<Ticker>>) {
                if (response.isSuccessful) {
                    val tickerList = response.body()
                    if (tickerList != null) {
                        mListener.onSucess(tickerList)
                    } else {
                        mListener.onEmpty("data empty")
                    }
                }
            }

            override fun onFailure(call: Call<List<Ticker>>, t: Throwable) {

                t.message?.let {
                    mListener.onFailure(it)
                }
            }
        })
    }

}