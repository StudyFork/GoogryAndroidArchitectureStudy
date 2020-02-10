package com.studyfork.architecturestudy.base

import androidx.annotation.NonNull
import androidx.databinding.ObservableField
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel {

    var observableToastMessage: ObservableField<String> = ObservableField("")

    private val compositeDisposable = CompositeDisposable()

    fun showToast(@NonNull message: String) {
        observableToastMessage.set(message)
    }

    protected fun Disposable.addDisposable() {
        compositeDisposable.add(this)
    }

    fun clearDisposable() {
        compositeDisposable.clear()
    }
}