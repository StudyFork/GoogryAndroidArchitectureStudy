package com.example.kyudong3.ui.movie.presenter

import com.example.kyudong3.data.model.Movie

interface MovieContract {
    interface View {
        fun showInvalidQuerySearch()
        fun showEmptySearchResult()
        fun setMovieData(movieList: List<Movie>)
        fun showNetworkError(error: Throwable)
        fun showCachedMovieData(movieList: List<Movie>)
    }

    interface Presenter {
        fun searchMovie(query: String)
    }
}