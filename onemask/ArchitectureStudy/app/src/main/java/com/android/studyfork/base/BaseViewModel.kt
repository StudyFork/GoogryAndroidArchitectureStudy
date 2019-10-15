package com.android.studyfork.base

import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel {

    protected val disposable = CompositeDisposable()

    fun clearDispoasble() {
        disposable.clear()
    }
}