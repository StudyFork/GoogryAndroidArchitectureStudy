package com.byiryu.study.ui.mvp.base

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<V : BaseContract.View> : BaseContract.Presenter<V> {

    var disposable = CompositeDisposable()

    var mvpView: V? = null

    override fun onAttach(view: V) {
        this.mvpView = view
    }

    override fun onDetach() {
        disposable.dispose()
        this.mvpView = null
    }


}