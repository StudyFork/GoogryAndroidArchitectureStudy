package com.example.seonoh.seonohapp.network

import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import com.example.seonoh.seonohapp.model.Market
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class CoinRequest(api: Api) {
    private val coinApi = api

    interface BaseResult<in T> {
        fun getNetworkSuccess(result: T)
        fun getNetworkFailed(code: String)
    }

    fun marketSend(listener: BaseResult<ArrayList<Market>>) {

        var single = coinApi.getMarketAll()

        single
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listener.getNetworkSuccess(it)
            }, { e ->
                if (e is HttpException) {
                    listener.getNetworkFailed(e.toString())


                } else {
                }
            })
    }

    fun sendCurrentPriceInfo(listener: BaseResult<ArrayList<CurrentPriceInfoModel>>, markets: String) {
        var single = coinApi.getCurrentPriceInfo(markets)

        single.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listener.getNetworkSuccess(it)

            }, { e ->
                if (e is HttpException) {
                    listener.getNetworkFailed(e.toString())

                } else {
                }
            })
    }
}