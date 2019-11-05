package com.android.studyfork.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    protected val disposable = CompositeDisposable()

    fun clearDispoasble() {
        disposable.clear()
    }
}