package com.example.studyfork.main

import com.example.studyfork.data.model.MovieSearchResponse

interface MainContract {
    interface View {
        fun searchMovie()
        fun showQueryEmpty()
        fun showMovieEmpty()
        fun showMovieError()
        fun showMovieList(list: List<MovieSearchResponse.MovieItem>)
        fun showRecentSearchListActivity()
    }

    interface Presenter {
        fun requestClearDisposable()
        fun requestMovieList(query: String)
    }
}