package io.github.jesterz91.study.presentation.common

interface BaseContract {

    interface View {
        fun showToast(message: String)
        fun hideSoftKeyboard()
        fun finish()
    }

    interface Presenter {
        fun clearDisposables()
    }
}