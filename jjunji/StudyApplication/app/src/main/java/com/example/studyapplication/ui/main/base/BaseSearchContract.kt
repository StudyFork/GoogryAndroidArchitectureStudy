package com.example.studyapplication.ui.main.base

interface BaseSearchContract {
    interface View {
        fun toastErrorMessage(message : String)
        fun toastEmptyQueryMessage()
    }

    interface Presenter {
        fun onRequestFailed(e : Throwable)
        fun checkQueryValid(query : String, validQuery : (Boolean) -> Unit)
    }
}