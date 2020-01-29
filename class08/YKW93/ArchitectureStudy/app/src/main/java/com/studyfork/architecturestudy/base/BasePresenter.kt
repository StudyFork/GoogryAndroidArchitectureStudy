package com.studyfork.architecturestudy.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BasePresenter : BaseContract.Presenter {

    private val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun Disposable.addDisposable() {
        compositeDisposable.add(this)
    }

    override fun clearDisposable() {
        compositeDisposable.clear()
    }
}