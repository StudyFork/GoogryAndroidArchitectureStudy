package com.architecture.study.base

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.architecture.study.data.repository.CoinRepository

abstract class BaseViewModel<T>(val repository: T) : ViewModel(){
    val exceptionMessage = ObservableField<String>()
}