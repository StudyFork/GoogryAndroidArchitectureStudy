package com.example.seonoh.seonohapp.contract

import android.content.Context
import com.example.seonoh.seonohapp.CoinAdapter
import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import com.example.seonoh.seonohapp.model.UseCoinModel

interface CoinFragmentContract {
    interface View{
        var presenter : Presenter
        fun initView()
        fun initRecyclerView(data: ArrayList<UseCoinModel>)
    }

    interface Presenter{
        fun loadData(marketName: String,context: Context)
        fun setData(data: ArrayList<UseCoinModel>)
        fun translateData(result: List<CurrentPriceInfoModel>,context : Context)
    }
}