package com.example.seonoh.seonohapp.network
import com.example.seonoh.seonohapp.SeonohApplication
import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class CurrentPriceInfoRequest{

    lateinit var mListener : ResultListener



    fun send(listener: ResultListener,markets : String): Disposable {

        mListener = listener
        val single = SeonohApplication.mApiService!!.getCurrentPriceInfo(markets)

        return single.subscribeOn(Schedulers.io())
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
        fun getCurrentInfoSuccess(result: CurrentPriceInfoModel)
        fun getCurrentInfoFailed(code : String)
    }
}