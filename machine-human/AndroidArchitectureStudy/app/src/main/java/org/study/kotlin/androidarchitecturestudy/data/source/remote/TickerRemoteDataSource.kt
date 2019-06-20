package org.study.kotlin.androidarchitecturestudy.data.source.remote

import android.util.Log
import org.study.kotlin.androidarchitecturestudy.api.UpbitApi
import org.study.kotlin.androidarchitecturestudy.api.model.MarketModel
import org.study.kotlin.androidarchitecturestudy.api.model.TickerModel
import org.study.kotlin.androidarchitecturestudy.base.BaseDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
***************************
BaseDataSource - structure

i = interface
f = function
***************************

i = BaseDataSource

    i = GetTickerListCallback

        f = onTickerListLoaded(tickerList: ArrayList<TickerModel>)
        f = onDataNotAvailable(error: String)

    f = requestMarkets(marketName: String, callback: GetTickerListCallback)

 */
object TickerRemoteDataSource : BaseDataSource {
    private val baseUrl = "https://api.upbit.com"
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UpbitApi::class.java)

    /**
        requestMarkets() = BaseDataSource의 requestMarkets()
     */
    override fun requestMarkets(marketName: String, callback: BaseDataSource.GetTickerListCallback) {
        Log.e("TAG TickerRemoteDataSource", "requestMarkets")
        retrofit.getMarket().enqueue(object : Callback<ArrayList<MarketModel>> {
            override fun onFailure(call: Call<ArrayList<MarketModel>>, t: Throwable) {
                Log.e("TAG TickerRemoteDataSource", "onFailure")
                callback.onDataNotAvailable(t.toString())
            }

            override fun onResponse(call: Call<ArrayList<MarketModel>>, response: Response<ArrayList<MarketModel>>) {
                Log.e("TAG TickerRemoteDataSource", "onResponse")

                response.let {
                    val responseList =
                        response.body()?.map { it.market }?.filter { it.substringBeforeLast("-") == marketName }
                    val coinList = responseList?.joinToString()
                    if (coinList != null) {
                        Log.e("TAG TickerRemoteDataSource", "null!")
                        getTickerList(coinList, callback)
                    }
                }
            }
        })
    }

    private fun getTickerList(markets: String, callback: BaseDataSource.GetTickerListCallback) {
        Log.e("TAG TickerRemoteDataSource", "getTickerList!")
        retrofit.getTicker(markets).enqueue(object : Callback<ArrayList<TickerModel>> {
            override fun onFailure(call: Call<ArrayList<TickerModel>>, t: Throwable) {
                Log.e("TAG TickerRemoteDataSource", "onFailure!")
                callback.onDataNotAvailable(t.toString())
            }

            override fun onResponse(call: Call<ArrayList<TickerModel>>, response: Response<ArrayList<TickerModel>>) {
                response.body()?.let {
                    Log.e("TAG TickerRemoteDataSource", "onResponse!")
                    callback.onTickerListLoaded(it)
                }
            }
        })
    }
}