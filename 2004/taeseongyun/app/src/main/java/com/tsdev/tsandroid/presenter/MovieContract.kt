package com.tsdev.tsandroid.presenter

import com.tsdev.tsandroid.data.Item


interface MovieContract {
    interface View {
        fun onHideSoftKeyboard()

        fun showToastMessage(message: String)

        fun hideProgressBar()

        fun showProgressBar()

        fun showSearchResult(items: List<Item>)
    }

    interface Presenter {
        fun loadMovie(query: String)
    }
}