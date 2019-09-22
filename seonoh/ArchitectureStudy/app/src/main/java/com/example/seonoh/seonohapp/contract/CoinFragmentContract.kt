package com.example.seonoh.seonohapp.contract

import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import com.example.seonoh.seonohapp.model.UseCoinModel

interface CoinFragmentContract {
    interface View {
        fun initRecyclerView(data: List<UseCoinModel>)
    }

    interface Presenter {
        fun loadData(marketName: String)
        fun setData(data: ArrayList<UseCoinModel>)
        fun translateData(result: List<CurrentPriceInfoModel>)
        fun clearDisposable()
    }
}