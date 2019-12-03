package com.ironelder.androidarchitecture.view.baseview

interface BaseContract {
    interface View

    interface Presenter<in VIEW : View> {
        fun attachView(view: VIEW)
        fun detachView()
        fun clearDisposable()
    }
}