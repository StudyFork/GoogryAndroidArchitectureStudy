package com.example.studyapplication.ui.main.base

interface BaseSearchContract {
    interface View {
        fun toastErrorConnFailed(message : String)
    }

    interface Presenter {
        fun onRequestFailed(e : Throwable)
    }
}