package io.github.jesterz91.study.presentation.common

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<V : BaseContract.View> : BaseContract.Presenter {

    abstract val view: V

    protected val disposables by lazy { CompositeDisposable() }

    override fun clearDisposables() = disposables.clear()
}