package com.example.seonoh.seonohapp.contract

import com.example.seonoh.seonohapp.model.UseCoinModel

interface CoinFragmentContract {
    interface View : BaseContract.View<Presenter>{
        fun setData(data : List<UseCoinModel>)
    }

    interface Presenter : BaseContract.Presenter<View>{
        fun loadData(marketName : String)
    }
}