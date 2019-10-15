package com.architecture.study.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<T>(val repository: T) : ViewModel(){
    val exceptionMessage = MutableLiveData<String>()
}