package com.example.seonoh.seonohapp.contract

import io.reactivex.disposables.CompositeDisposable

interface BaseContract {

    interface View<T> {
        val presenter: T
    }

    interface Presenter<T> {
        val view: T
        val compositeDisposable: CompositeDisposable
        fun clearDisposable()
    }
}