package com.egiwon.architecturestudy.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter : BaseContract.Presenter {
    override val compositeDisposable: CompositeDisposable = CompositeDisposable()

    protected fun Disposable.addDisposable() {
        compositeDisposable.add(this)
    }

    override fun clearDisposable() {
        compositeDisposable.clear()
    }

    override fun disposeDisposable() {
        compositeDisposable.dispose()
    }
}