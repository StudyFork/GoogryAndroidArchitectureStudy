package com.hansung.firstproject.ui.main

import com.hansung.firstproject.data.MovieResponseModel

interface MainContract {
    interface View {
        fun removeKeyboard(): Boolean

        fun showErrorKeywordEmpty()

        fun showErrorInternetDisconnect()

        fun showErrorEmptyList()

        fun addItemToAdapter(response: MovieResponseModel)
    }

    interface Presenter {
        fun doSearch(keyword: String)
    }
}