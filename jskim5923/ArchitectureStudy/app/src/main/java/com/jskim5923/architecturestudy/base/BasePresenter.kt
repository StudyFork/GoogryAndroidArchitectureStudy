package com.jskim5923.architecturestudy.base

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter : BaseContract.Presenter {
    protected val compositeDisposable = CompositeDisposable()

    override fun clearCompositeDisposable() {
        compositeDisposable.clear()
    }
}