package com.example.seonoh.seonohapp.contract

import io.reactivex.disposables.CompositeDisposable

interface BaseContract {

    interface View<in T>{
        val presenter : Presenter
        fun setData(data : T)
    }

    interface Presenter{
        val compositeDisposable : CompositeDisposable
        fun loadData(marketName : String? = null)
        fun clearDisposable()
    }
}