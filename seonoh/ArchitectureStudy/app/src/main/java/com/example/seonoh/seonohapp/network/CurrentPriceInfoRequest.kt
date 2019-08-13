package com.example.seonoh.seonohapp.network
import com.example.seonoh.seonohapp.SeonohApplication
import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class CurrentPriceInfoRequest{

    lateinit var mListener : ResultListener



    fun send(listener: ResultListener,markets : String) {

        mListener = listener
        var single = SeonohApplication.mApiService!!.getCurrentPriceInfo(markets)

        single.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mListener.getCurrentInfoSuccess(it)

            }, { e ->
                if (e is HttpException) {
                    mListener.getCurrentInfoFailed(e.toString())


                } else {
                }
            })
    }




    interface ResultListener {
        fun getCurrentInfoSuccess(result: ArrayList<CurrentPriceInfoModel>)
        fun getCurrentInfoFailed(code : String)
    }
}