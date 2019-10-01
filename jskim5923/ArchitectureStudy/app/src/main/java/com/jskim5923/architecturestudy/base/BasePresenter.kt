package com.jskim5923.architecturestudy.base

import io.reactivex.disposables.CompositeDisposable


interface BasePresenter<T> {
    val view: T

    val compositeDisposable: CompositeDisposable

    fun clearCompositeDisposable()
}