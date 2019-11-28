package com.egiwon.architecturestudy.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter : BaseContract.Presenter {
    private val compositeDisposable = CompositeDisposable()

    protected fun clearDisposable() {
        compositeDisposable.clear()
    }

    protected fun disposeDisposable() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
}