package com.nanamare.mac.sample.base

import androidx.lifecycle.MutableLiveData

abstract class BaseViewModel {

    var isLoadingObservable = MutableLiveData<Boolean>().apply {
        value = false
    }

    abstract fun close()

}