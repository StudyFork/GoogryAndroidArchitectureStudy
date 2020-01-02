package com.example.studyapplication.ui.main.base

import android.util.Log

open class BaseSearchPresenter(
    open val view : BaseSearchContract.View
) : BaseSearchContract.Presenter {

    override fun onRequestFailed(e: Throwable) {
        Log.e("test", ">>> onRequestFailed")
        e.message?.let { view.toastErrorConnFailed(it) }
    }
}