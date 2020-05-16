package com.tsdev.tsandroid.ui.viewmodel

import com.tsdev.tsandroid.ui.observe.ObserverProvider
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel {

    internal val compositeDisposable = CompositeDisposable()

    fun onDestroy() {
        compositeDisposable.clear()
    }

    abstract val observe: ObserverProvider
}