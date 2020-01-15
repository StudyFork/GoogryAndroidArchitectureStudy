package com.example.studyapplication.ui.main.base

open class BaseSearchPresenter(
    open val view : BaseSearchContract.View
) : BaseSearchContract.Presenter {

    override fun onRequestFailed(e: Throwable) {
        e.message?.let { view.toastErrorMessage(it) }
    }

    override fun checkQueryValid(query: String, validQuery: (Boolean) -> Unit) {
        if(query.isEmpty()) {
            view.toastEmptyQueryMessage()
            validQuery(false)
        } else {
            validQuery(true)
        }
    }
}