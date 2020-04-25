package com.eunice.eunicehong.ui

import android.content.Context
import com.eunice.eunicehong.data.model.MovieList
import com.eunice.eunicehong.data.source.MovieDataSource

interface MovieContract {
    interface View {
        var movieContext: Context

        fun showSearchResult(movies: MovieList)
        fun showRemoveHistoryConfirmDialog()
    }

    interface Presenter {
        fun showDetail(url: String)
        fun search(query: String, callback: MovieDataSource.LoadMoviesCallback)
        fun removeHistory()
    }
}