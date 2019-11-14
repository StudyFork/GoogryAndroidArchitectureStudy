package com.ironelder.androidarchitecture.view.baseview

abstract class BasePresenter<VIEW : BaseContract.View> : BaseContract.Presenter<VIEW> {
    protected lateinit var view: VIEW
        private set

    override fun attachView(view: VIEW) {
        this.view = view
    }

    override fun detachView() {
//TODO : 추후에 Fragment가 여러개가 존재할 경우 lateinit을 해지하고 BasePresenter를 활용할 수 있도록 null을 통한 GC기능이 동작되도록 한다.
//        view = null
    }
}