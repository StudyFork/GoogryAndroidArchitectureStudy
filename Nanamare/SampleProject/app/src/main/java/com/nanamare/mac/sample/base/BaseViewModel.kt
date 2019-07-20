package com.nanamare.mac.sample.base

import androidx.lifecycle.MutableLiveData

abstract class BaseViewModel {

    val isLoadingObservable = MutableLiveData<Boolean>().apply {
        value = false
    }

    abstract fun close()

}