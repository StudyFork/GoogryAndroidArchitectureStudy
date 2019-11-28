package com.egiwon.architecturestudy.base

import io.reactivex.disposables.CompositeDisposable

interface BaseContract {
    interface View
    interface Presenter {
        fun clearDisposable()
        fun disposeDisposable()
        val compositeDisposable: CompositeDisposable
    }
}