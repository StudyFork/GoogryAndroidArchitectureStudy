package com.example.dkarch.presentation.base

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter(override val view: BaseContract.View) : BaseContract.Presenter {
    protected val compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        compositeDisposable.clear()
    }
}
