package com.example.seonoh.seonohapp.network
import com.example.seonoh.seonohapp.model.Market
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class MarketRequest : BaseReqeust(){


    lateinit var mListener : ResultListener

    fun send(listener: ResultListener)  {

        mListener = listener


        var single = api!!.getMarketAll()

        single
            .subscribeOn(Schedulers.io())
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
        fun getMarketSuccess(result: ArrayList<Market>)
        fun getMarketFailed(code : String)
    }
}