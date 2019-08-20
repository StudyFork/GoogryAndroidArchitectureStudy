package com.example.seonoh.seonohapp.network

import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import com.example.seonoh.seonohapp.model.Market
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class CoinRequest(api: Api) {
    private val mApi = api

    fun marketSend(listener: MarketResultListener) {

        var single = mApi.getMarketAll()

        single
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listener.getMarketSuccess(it)
            }, { e ->
                if (e is HttpException) {
                    listener.getMarketFailed(e.toString())


                } else {
                }
            })
    }


    interface MarketResultListener {
        fun getMarketSuccess(result: ArrayList<Market>)
        fun getMarketFailed(code: String)
    }


    fun currentPriceInfoSend(listener: CurrentPriceInfoResultListener, markets: String) {
        var single = mApi.getCurrentPriceInfo(markets)

        single.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listener.getCurrentInfoSuccess(it)

            }, { e ->
                if (e is HttpException) {
                    listener.getCurrentInfoFailed(e.toString())

                } else {
                }
            })
    }


    interface CurrentPriceInfoResultListener {
        fun getCurrentInfoSuccess(result: ArrayList<CurrentPriceInfoModel>)
        fun getCurrentInfoFailed(code: String)
    }


}