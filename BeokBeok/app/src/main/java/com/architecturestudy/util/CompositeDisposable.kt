package com.architecturestudy.util

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

object CompositeDisposable {
    private var compositeDisposable = CompositeDisposable()

    fun add(disposable: Disposable) =
        getCompositeDisposable().add(disposable)

    fun dispose() = getCompositeDisposable().dispose()

    private fun getCompositeDisposable(): CompositeDisposable {
        if (compositeDisposable.isDisposed) {
            compositeDisposable = CompositeDisposable()
        }
        return compositeDisposable
    }
}