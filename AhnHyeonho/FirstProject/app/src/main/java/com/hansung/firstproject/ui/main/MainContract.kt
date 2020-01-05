package com.hansung.firstproject.ui.main

import com.hansung.firstproject.data.MovieResponseModel

interface MainContract {
    interface View {
        fun initRecyclerView()

        fun removeKeyboard(): Boolean

        fun showErrorKeywordEmpty()

        fun showErrorInternetDisconnect()

        fun addItemToAdapter(response: MovieResponseModel)
    }

    interface Presenter {
        fun doSearch(keyword: String)
    }
}