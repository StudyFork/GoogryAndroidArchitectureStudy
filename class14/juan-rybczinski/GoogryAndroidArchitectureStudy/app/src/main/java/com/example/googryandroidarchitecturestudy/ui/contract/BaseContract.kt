package com.example.googryandroidarchitecturestudy.ui.contract

import com.example.googryandroidarchitecturestudy.domain.Movie
import com.example.googryandroidarchitecturestudy.domain.UrlResource

interface BaseContract {
    interface View {
        fun showProgressBar()
        fun hideProgressBar()
        fun hideKeyboard()
        fun showUrlResource(item: UrlResource)
        fun showInvalidUrl()
        fun showQueryEmpty()
        fun showNoSearchResult()
        fun showSearchFailed(e: Exception)
        fun showMovieList(items: List<Movie>)
    }

    interface Presenter {
        fun selectUrlItem(item: UrlResource)
        suspend fun queryMovieList(query: String)
    }
}