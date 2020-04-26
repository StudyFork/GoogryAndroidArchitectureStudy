package com.tsdev.tsandroid.base

import com.tsdev.tsandroid.eventbus.RxEventBus
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BasePresenter<VIEW : BaseContract.View> : BaseContract.Presenter {
    abstract val view: VIEW

    abstract val rxEventBus: RxEventBus

    protected val compositeDisposable = CompositeDisposable()

    override fun clearCompositeDisposable() {
        compositeDisposable.clear()
    }
}