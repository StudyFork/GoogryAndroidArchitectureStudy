package com.jskim5923.architecturestudy.base

import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel {
    protected val compositeDisposable = CompositeDisposable()

    fun clearCompositeDisposable() {
        compositeDisposable.clear()
    }
}