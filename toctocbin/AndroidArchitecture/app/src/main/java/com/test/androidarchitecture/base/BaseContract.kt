package com.test.androidarchitecture.base

interface BaseContract {

    interface View<P> {

        val presenter: P

        fun showToast(msg: String)
    }

    interface Presenter {

        fun clearDisposable()
    }
}