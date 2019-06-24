package org.study.kotlin.androidarchitecturestudy.data.source.remote

import android.util.Log
import org.study.kotlin.androidarchitecturestudy.api.model.MarketModel
import org.study.kotlin.androidarchitecturestudy.api.model.TickerModel
import org.study.kotlin.androidarchitecturestudy.api.retorifit.RetrofitBuilder
import org.study.kotlin.androidarchitecturestudy.base.BaseDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
class TickerRemoteDataSource private constructor(
) : BaseDataSource, RetrofitBuilder() {

    /**
    requestMarkets() = BaseDataSource의 requestMarkets()
     */
    override fun requestMarkets(marketName: String, callback: BaseDataSource.GetTickerListCallback) {
        retrofit.getMarket().enqueue(object : Callback<ArrayList<MarketModel>> {
            override fun onFailure(call: Call<ArrayList<MarketModel>>, t: Throwable) {
                callback.onDataNotAvailable(t)
            }

            override fun onResponse(call: Call<ArrayList<MarketModel>>, response: Response<ArrayList<MarketModel>>) {
                response.let {
                    val responseList =
                        response.body()?.map { it.market }?.filter {
                            it.substringBeforeLast("-") == marketName
                        }
                    val coinList = responseList?.joinToString()
                    coinList?.let { getTickerList(it, callback) }
                }
            }
        })
    }

    private fun getTickerList(markets: String, callback: BaseDataSource.GetTickerListCallback) {
        retrofit.getTicker(markets).enqueue(object : Callback<ArrayList<TickerModel>> {
            override fun onFailure(call: Call<ArrayList<TickerModel>>, t: Throwable) {
                callback.onDataNotAvailable(t)
            }

            override fun onResponse(call: Call<ArrayList<TickerModel>>, response: Response<ArrayList<TickerModel>>) {
                response.body()?.let { callback.onTickerListLoaded(it) }
            }
        })
    }

    companion object {
        //static 접근을 허용할 프로터피/함수등 입력
        private var instance: TickerRemoteDataSource? = null

        operator fun invoke(): TickerRemoteDataSource {
            Log.e("TAG", "remoteinvoke")
            return instance ?: TickerRemoteDataSource()
                .apply { instance = this }

        }
    }
}