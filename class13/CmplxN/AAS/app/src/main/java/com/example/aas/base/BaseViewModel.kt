package com.example.aas.base

import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel {
    protected val compositeDisposable = CompositeDisposable()

    open fun onDestroy() {
        compositeDisposable.clear()
    }
}