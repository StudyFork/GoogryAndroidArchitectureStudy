package com.nanamare.mac.sample.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {

    val isLoadingObservable = MutableLiveData<Boolean>().apply {
        value = false
    }

    abstract fun close()

    override fun onCleared() {
        close()
        super.onCleared()
    }
}