package com.example.seonoh.seonohapp.network
import com.example.seonoh.seonohapp.SeonohApplication
import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import com.example.seonoh.seonohapp.model.Market
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class MarketRequest{

    lateinit var mListener : ResultListener



    fun send(listener: ResultListener) {

        mListener = listener
        var single = SeonohApplication.mApiService!!.getMarketAll()

        single.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mListener.getMarketSuccess(it)
            }, { e ->
                if (e is HttpException) {
                    mListener.getMarketFailed(e.toString())


                } else {
                }
            })
    }




    interface ResultListener {
        fun getMarketSuccess(result: Market)
        fun getMarketFailed(code : String)
    }
}