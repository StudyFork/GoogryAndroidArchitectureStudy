package com.example.seonoh.seonohapp.contract

interface BaseContract {

    interface View<in T>{
        val presenter : Presenter
        fun setData(data : T)
    }

    interface Presenter{
        fun loadData(marketName : String = "")
        fun clearDisposable()
    }
}