package com.studyfork.architecturestudy.base

import androidx.annotation.NonNull
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    var liveDataToastMessage: MutableLiveData<String> = MutableLiveData()

    private val compositeDisposable = CompositeDisposable()

    fun showToast(@NonNull message: String) {
        liveDataToastMessage.value = message
    }

    protected fun Disposable.addDisposable() {
        compositeDisposable.add(this)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}