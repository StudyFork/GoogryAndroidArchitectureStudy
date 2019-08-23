package com.example.seonoh.seonohapp.network

import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import com.example.seonoh.seonohapp.model.Market
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class CoinRequest(private val api: Api) {

    interface BaseResult<in T> {
        fun getNetworkSuccess(result: T)
        fun getNetworkFailed(code: String)
    }

    fun sendMarket(listener: BaseResult<ArrayList<Market>>) {

        val single = api.getMarketAll()

        single
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listener.getNetworkSuccess(it)
            }, { e ->
                if (e is HttpException) {
                    listener.getNetworkFailed(e.toString())
                }
            })
    }

    fun sendCurrentPriceInfo(listener: BaseResult<ArrayList<CurrentPriceInfoModel>>, markets: String) {
        val single = api.getCurrentPriceInfo(markets)

        single.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listener.getNetworkSuccess(it)

            }, { e ->
                if (e is HttpException) {
                    listener.getNetworkFailed(e.toString())
                }
            })
    }
}