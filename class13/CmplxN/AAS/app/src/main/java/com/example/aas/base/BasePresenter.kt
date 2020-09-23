package com.example.aas.base

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter(protected open val view: BaseContract.View) : BaseContract.Presenter {
    protected val compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        compositeDisposable.clear()
    }
}