package com.example.seonoh.seonohapp.network

import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import com.example.seonoh.seonohapp.model.Market
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class CoinRequest {
    lateinit var mMarketListener: MarketResultListener

    fun marketSend(api: Api, listener: MarketResultListener) {

        mMarketListener = listener

        var single = api!!.getMarketAll()

        single
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mMarketListener.getMarketSuccess(it)
            }, { e ->
                if (e is HttpException) {
                    mMarketListener.getMarketFailed(e.toString())


                } else {
                }
            })
    }


    interface MarketResultListener {
        fun getMarketSuccess(result: ArrayList<Market>)
        fun getMarketFailed(code: String)
    }


    lateinit var mCurrentPriceInfoListener: CurrentPriceInfoResultListener


    fun currentPriceInfoSend(api: Api, listener: CurrentPriceInfoResultListener, markets: String) {
        mCurrentPriceInfoListener = listener
        var single = api.getCurrentPriceInfo(markets)

        single.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mCurrentPriceInfoListener.getCurrentInfoSuccess(it)

            }, { e ->
                if (e is HttpException) {
                    mCurrentPriceInfoListener.getCurrentInfoFailed(e.toString())


                } else {
                }
            })
    }


    interface CurrentPriceInfoResultListener {
        fun getCurrentInfoSuccess(result: ArrayList<CurrentPriceInfoModel>)
        fun getCurrentInfoFailed(code: String)
    }


}