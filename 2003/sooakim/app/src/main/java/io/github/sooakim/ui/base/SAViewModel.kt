package io.github.sooakim.ui.base

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.sooakim.presentation.SAViewModelLifecycle
import io.github.sooakim.util.SingleEvent
import io.github.sooakim.util.ext.liveOf
import io.reactivex.disposables.CompositeDisposable

open class SAViewModel<S : SAState> : ViewModel(), SAViewModelLifecycle {
    protected val compositeDisposable: CompositeDisposable by lazy(::CompositeDisposable)

    private val _state: MutableLiveData<SingleEvent<S>> by liveOf()
    val state: LiveData<SingleEvent<S>> = _state

    @MainThread
    protected fun runState(state: S) {
        _state.value = SingleEvent(state)
    }

    protected fun postState(state: S) {
        _state.postValue(SingleEvent(state))
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}