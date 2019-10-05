package com.example.seonoh.seonohapp.contract

import com.example.seonoh.seonohapp.model.UseCoinModel

interface CoinFragmentContract {
    interface View : BaseContract.View{
        fun setData(data : List<UseCoinModel>)
    }
}