package com.android.studyfork.base


interface BaseContract {

    interface View<P> {
        val presenter: P
    }

    interface Presenter {
        fun clearDisposable()
    }

}
