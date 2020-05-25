package com.tsdev.tsandroid.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.tsdev.tsandroid.ui.observe.ObserverProvider
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    internal val compositeDisposable by lazy { CompositeDisposable() }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    abstract val observe: ObserverProvider
}