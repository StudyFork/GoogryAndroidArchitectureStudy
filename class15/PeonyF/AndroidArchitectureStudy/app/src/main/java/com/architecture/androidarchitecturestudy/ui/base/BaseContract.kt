package com.architecture.androidarchitecturestudy.ui.base

interface BaseContract {
    interface View {
        fun showMessage(message: String)
    }

    interface Presenter {
    }
}