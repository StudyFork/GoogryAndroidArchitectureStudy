package com.example.studyapplication.ui.main.base

open class BaseSearchPresenter(
    open val view : BaseSearchContract.View
) : BaseSearchContract.Presenter {

    override fun onRequestFailed(e: Throwable) {
        e.message?.let { view.toastErrorMessage(it) }
    }
}