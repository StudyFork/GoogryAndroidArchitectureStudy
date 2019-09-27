package com.example.seonoh.seonohapp.base

interface BaseContract {

    interface View{
        val presenter : Presenter
    }

    interface Presenter{
        fun clearDisposable()
    }
}