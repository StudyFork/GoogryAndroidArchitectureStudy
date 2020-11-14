package com.example.studyfork

import com.example.studyfork.model.MovieSearchResponse

interface MainContract {
    interface View {
        fun showQueryEmpty()
        fun showEmptyMovie()
        fun showMovieList(list: List<MovieSearchResponse.MovieItem>)
    }

    interface Presenter {
        fun requestMovieList(query: String)
    }
}