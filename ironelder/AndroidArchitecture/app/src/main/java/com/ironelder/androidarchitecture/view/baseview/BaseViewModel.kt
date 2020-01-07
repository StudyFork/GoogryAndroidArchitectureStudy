package com.ironelder.androidarchitecture.view.baseview

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel(){

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    protected fun Disposable.addDisposable() {
        compositeDisposable.add(this)
    }

    open fun clearDisposable() {
        compositeDisposable.clear()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}