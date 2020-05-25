package com.hwaniiidev.architecture.ui.main

import com.hwaniiidev.architecture.model.Item

interface MainContract {
    interface View {
        fun showQueryIsEmpty()
        fun hideKeyBoard()
        fun showResponseIsNone()
        fun showResponseError()
        fun showNetworkFailure()
        fun showMoviesList(items: List<Item>)
    }

    interface Presenter {
        fun searchMovies(query: String)
    }
}