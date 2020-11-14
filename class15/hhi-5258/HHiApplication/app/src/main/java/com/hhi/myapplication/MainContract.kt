package com.hhi.myapplication

import com.hhi.myapplication.data.model.MovieData

interface MainContract {
    interface View {
        fun showMovies(items: ArrayList<MovieData.MovieItem>)
        fun showEmptyQuery()
    }

    interface Presenter {
        fun searchMovie(query: String)
    }
}