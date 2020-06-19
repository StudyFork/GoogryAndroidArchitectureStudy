package com.project.architecturestudy.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private val disposable = CompositeDisposable()

    protected fun Disposable.addDisposable() {
        disposable.add(this)
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}