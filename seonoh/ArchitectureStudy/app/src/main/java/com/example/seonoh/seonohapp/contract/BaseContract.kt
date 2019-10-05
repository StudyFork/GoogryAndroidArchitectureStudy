package com.example.seonoh.seonohapp.contract

import io.reactivex.disposables.CompositeDisposable

interface BaseContract {

    interface View {
        val presenter : Presenter<View>
        fun showToast()
    }

    interface FragmentView

    interface Presenter<V> {
        val view: V
        val compositeDisposable: CompositeDisposable
        fun clearDisposable()
    }
}