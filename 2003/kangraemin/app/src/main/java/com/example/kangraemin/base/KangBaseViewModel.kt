package com.example.kangraemin.base

import io.reactivex.disposables.CompositeDisposable

abstract class KangBaseViewModel {
    protected val compositeDisposable = CompositeDisposable()

    fun onDestroy() {
        compositeDisposable.dispose()
    }
}