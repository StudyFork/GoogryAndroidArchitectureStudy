package org.study.kotlin.androidarchitecturestudy.base

interface BaseViewModel {
    fun onDataNotAvailable(errorMessage: Throwable)
}