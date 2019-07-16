package org.study.kotlin.androidarchitecturestudy.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel{
    abstract fun onDataNotAvailable(errorMessage: Throwable)
}