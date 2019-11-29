package com.egiwon.architecturestudy.base

import io.reactivex.disposables.CompositeDisposable

open class BasePresenter : BaseContract.Presenter {
    override val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun clearDisposable() {
        compositeDisposable.clear()
    }

    override fun disposeDisposable() {
        compositeDisposable.dispose()
    }
}