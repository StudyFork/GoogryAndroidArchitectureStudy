package com.test.androidarchitecture.base

import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel {

    protected val compositeDisposable = CompositeDisposable()

    fun clearDisposable() {
        compositeDisposable.clear()
    }
}