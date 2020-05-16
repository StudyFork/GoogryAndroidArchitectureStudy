package io.github.jesterz91.study.presentation.common

import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel {

    protected val disposables by lazy { CompositeDisposable() }

    fun clearDisposables() = disposables.clear()
}