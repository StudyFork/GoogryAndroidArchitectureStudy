package com.example.seonoh.seonohapp


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import com.example.seonoh.seonohapp.network.CurrentPriceInfoRequest


class KrwFrgment : Fragment(),CurrentPriceInfoRequest.ResultListener {

    lateinit var mView : View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.krw_fragment, container, false)

        CurrentPriceInfoRequest().send(this,"KRW-BTC,BTC-BCC")

        return mView
    }

    override fun getCurrentInfoSuccess(result: CurrentPriceInfoModel) {
        Log.e("getCurrentInfoSuccess","getCurrentInfoSuccess !!")
    }

    override fun getCurrentInfoFailed(code: String) {
        Log.e("getCurrentInfoFailed","getCurrentInfoFailed !!")
    }

    companion object{
        fun newInstance(): KrwFrgment {
            return KrwFrgment()
        }
    }
}
