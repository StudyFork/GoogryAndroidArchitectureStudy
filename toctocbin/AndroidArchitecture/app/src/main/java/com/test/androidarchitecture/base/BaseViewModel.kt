package com.test.androidarchitecture.base

import androidx.databinding.ObservableField
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel {

    protected val compositeDisposable = CompositeDisposable()
    val toastMessage = ObservableField<String>()

    fun clearDisposable() {
        compositeDisposable.clear()
    }
}