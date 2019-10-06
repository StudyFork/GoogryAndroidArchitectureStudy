package com.example.seonoh.seonohapp.contract

interface BaseContract {

    interface View<P> {
        val presenter : Presenter
        fun showToast()
    }

    interface Presenter {

        fun clearDisposable()
    }
}