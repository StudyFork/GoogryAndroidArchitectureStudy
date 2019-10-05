package com.jskim5923.architecturestudy.base

interface BaseContract {

    interface View {
        fun initView()
    }

    interface Presenter {
        fun clearCompositeDisposable()
    }
}