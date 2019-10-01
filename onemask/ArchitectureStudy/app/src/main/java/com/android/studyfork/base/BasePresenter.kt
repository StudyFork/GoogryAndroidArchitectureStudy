package com.android.studyfork.base

import io.reactivex.disposables.CompositeDisposable

interface BasePresenter<T> {
    val view : T
    val disposables : CompositeDisposable

    fun clearDisposable()
}