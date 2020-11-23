package com.example.studyfork

import com.example.studyfork.data.model.MovieSearchResponse

interface MainContract {
    interface View {
        fun showQueryEmpty()
        fun showMovieEmpty()
        fun showMovieError()
        fun showMovieList(list: List<MovieSearchResponse.MovieItem>)
    }

    interface Presenter {
        fun requestClearDisposable()
        fun requestMovieList(query: String)
    }
}