package com.nanamare.mac.sample.api

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class DisposableManager {

    private var disposable: CompositeDisposable? = null

    fun add(disposable: Disposable) {
        getCompositeDisposable()?.add(disposable)
    }

    fun dispose() {
        getCompositeDisposable()?.dispose()
    }

    private fun getCompositeDisposable(): CompositeDisposable? {
        if (disposable?.isDisposed == true) {
            disposable = CompositeDisposable()
        }
        return disposable
    }
}