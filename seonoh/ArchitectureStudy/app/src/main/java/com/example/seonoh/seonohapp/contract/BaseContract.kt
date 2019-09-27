package com.example.seonoh.seonohapp.contract

interface BaseContract {

    interface View{
        val presenter : Presenter
    }

    interface Presenter{
        fun clearDisposable()
    }
}