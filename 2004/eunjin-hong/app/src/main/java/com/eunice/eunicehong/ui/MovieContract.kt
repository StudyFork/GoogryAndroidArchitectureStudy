package com.eunice.eunicehong.ui

import android.content.Context
import com.eunice.eunicehong.data.model.Movie

interface MovieContract {
    interface View {
        var movieContext: Context
        fun showSearchResult(movies: List<Movie>)
        fun showRemoveHistoryConfirmDialog()
    }

    interface Presenter {
        fun showDetail(url: String)
        fun search(query: String)
        fun removeHistory()
    }
}