package org.study.kotlin.androidarchitecturestudy.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel(){
    abstract fun onDataNotAvailable(errorMessage: Throwable)
}