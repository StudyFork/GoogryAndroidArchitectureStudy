package com.deepco.studyfork.presenter

interface BaseContract {
    interface View {
        fun showToastMessage(message: String)
    }

    interface Presenter
}