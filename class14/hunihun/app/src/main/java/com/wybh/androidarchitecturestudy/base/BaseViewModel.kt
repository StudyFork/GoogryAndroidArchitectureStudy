package com.wybh.androidarchitecturestudy.base
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel: ViewModel() {
    private val composeDisposable = CompositeDisposable()
    val progressVisible = MutableLiveData<Int>(View.GONE)

    override fun onCleared() {
        composeDisposable.dispose()
        super.onCleared()
    }

    fun addDisposable(disposable: Disposable) {
        composeDisposable.add(disposable)
    }
}