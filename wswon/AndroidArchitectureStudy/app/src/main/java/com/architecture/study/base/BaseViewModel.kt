package com.architecture.study.base

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<T>(val repository: T) : ViewModel(){
    val exceptionMessage = ObservableField<String>()
}