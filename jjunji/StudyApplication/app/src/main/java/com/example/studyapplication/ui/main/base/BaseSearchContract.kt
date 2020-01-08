package com.example.studyapplication.ui.main.base

interface BaseSearchContract {
    interface View {
        fun toastErrorMessage(message : String)
    }

    interface Presenter {
        fun onRequestFailed(e : Throwable)
    }
}