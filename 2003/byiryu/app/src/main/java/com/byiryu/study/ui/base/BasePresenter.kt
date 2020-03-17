package com.byiryu.study.ui.base

import io.reactivex.disposables.Disposable

abstract class BasePresenter<V : BaseContract.View> : BaseContract.Presenter<V> {

    var disposable: Disposable? = null

    var mvpView: V? = null

    override fun onAttach(view: V) {
        this.mvpView = view
    }

    override fun onDetach() {
        disposable?.dispose()
        this.mvpView = null
    }


}