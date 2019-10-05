package com.test.androidarchitecture.base

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter : BaseContract.Presenter {

    protected val compositeDisposable = CompositeDisposable()

    override fun clearDisposable() {
        compositeDisposable.clear()
    }
}