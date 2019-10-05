package com.example.seonoh.seonohapp.contract

import com.example.seonoh.seonohapp.model.Market

interface CoinMainContract : BaseContract {
    interface View : BaseContract.View{
        fun setData(data : List<Market>)
    }
}