package io.github.jesterz91.study.common

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    protected val disposables by lazy { CompositeDisposable() }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}