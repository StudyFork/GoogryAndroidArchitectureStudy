package com.tsdev.tsandroid.base

interface BaseContract {
    interface View {
        fun onHideSoftKeyboard()

        fun showToastMessage(message: String)
    }

    interface Presenter {
        fun onPressBackButton()

        fun clearCompositeDisposable()
    }
}