package com.example.study.util.base

import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel {

    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun clearDisposable() {
        compositeDisposable.clear()
    }
}