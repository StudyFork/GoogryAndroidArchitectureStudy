package com.test.androidarchitecture.base

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    protected val compositeDisposable = CompositeDisposable()
    val toastMessage = ObservableField<String>()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}