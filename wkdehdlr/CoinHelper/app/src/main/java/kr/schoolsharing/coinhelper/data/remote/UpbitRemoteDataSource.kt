package kr.schoolsharing.coinhelper.data.remote

import kr.schoolsharing.coinhelper.data.UpbitDataSource
import kr.schoolsharing.coinhelper.model.UpbitMarket
import kr.schoolsharing.coinhelper.model.UpbitTicker
import kr.schoolsharing.coinhelper.network.UpbitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UpbitRemoteDataSource : UpbitDataSource {

    private const val BASE_URL = "https://api.upbit.com/"
    private val retrofit: Retrofit
    private val upbitService: UpbitService

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        upbitService = retrofit.create(UpbitService::class.java)
    }

    override fun getMarket(callback: UpbitDataSource.GetMarketCallback) {
        upbitService
            .getUpBitMarket()
            .enqueue(object : Callback<List<UpbitMarket>> {

                override fun onFailure(call: Call<List<UpbitMarket>>, t: Throwable) {
                    callback.onDataNotAvailable(t)
                }

                override fun onResponse(call: Call<List<UpbitMarket>>, response: Response<List<UpbitMarket>>) {
                    response.body()?.let {
                        callback.onMarketLoaded(it)
                    }
                }

            })
    }

    override fun getTicker(markets: String, callback: UpbitDataSource.GetTickerCallback) {
        upbitService
            .getUpBitTicker(markets)
            .enqueue(object : Callback<List<UpbitTicker>> {

                override fun onFailure(call: Call<List<UpbitTicker>>, t: Throwable) {
                    callback.onDataNotAvailable(t)
                }

                override fun onResponse(call: Call<List<UpbitTicker>>, response: Response<List<UpbitTicker>>) {
                    response.body()?.let {
                        callback.onTickerLoaded(it)
                    }
                }
            })
    }


}