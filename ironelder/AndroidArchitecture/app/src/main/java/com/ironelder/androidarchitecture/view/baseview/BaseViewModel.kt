package com.ironelder.androidarchitecture.view.baseview

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    protected fun Disposable.addDisposable() {
        compositeDisposable.add(this)
    }

    protected fun clearDisposable() {
        compositeDisposable.clear()
    }
}