package com.architecture.study.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel :
    ViewModel() {
    override fun onCleared() {
        super.onCleared()
    }
}