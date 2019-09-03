package com.example.seonoh.seonohapp.contract

import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import com.example.seonoh.seonohapp.model.UseCoinModel

interface CoinFragmentContract {
    interface View {
        fun initView()
        fun initRecyclerView(data: ArrayList<UseCoinModel>)
    }

    interface Presenter {
        fun loadData(marketName: String)
        fun setData(data: ArrayList<UseCoinModel>)
        fun translateData(result: List<CurrentPriceInfoModel>)
        fun clearDisposable()
    }
}