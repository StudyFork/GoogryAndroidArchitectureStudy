package com.tsdev.tsandroid.presenter


interface MovieContract {
    interface View {
        fun onHideSoftKeyboard()

        fun showToastMessage(message: String)

        fun hideProgressBar()

        fun showProgressBar()
    }

    interface Presenter {
        fun loadMovie(query: String)
    }
}