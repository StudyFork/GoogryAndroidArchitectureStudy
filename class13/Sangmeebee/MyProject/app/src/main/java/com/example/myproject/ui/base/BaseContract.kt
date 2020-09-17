package com.example.myproject.ui.base

interface BaseContract {
    interface View {
        fun networkError(errorMessage: String)
    }

    interface Presenter {
    }
}
