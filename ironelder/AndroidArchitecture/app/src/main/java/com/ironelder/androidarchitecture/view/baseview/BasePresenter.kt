package com.ironelder.androidarchitecture.view.baseview

abstract class BasePresenter<VIEW : BaseContract.View> : BaseContract.Presenter<VIEW> {
    protected var view: VIEW? = null
        private set

    override fun attachView(view: VIEW) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }
}