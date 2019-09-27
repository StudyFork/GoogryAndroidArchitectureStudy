package com.example.seonoh.seonohapp.contract

import com.example.seonoh.seonohapp.base.BaseContract
import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import com.example.seonoh.seonohapp.model.UseCoinModel

interface CoinFragmentContract {
    interface View : BaseContract.View {
        fun initCoinMarketTicker(data: List<UseCoinModel>)
    }

    interface Presenter : BaseContract.Presenter {
        fun loadData(marketName: String)
        fun translateData(result: List<CurrentPriceInfoModel>) : ArrayList<UseCoinModel>
    }
}