package com.architecturestudy.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable?) = disposable?.let { compositeDisposable.add(it) }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}