package com.song2.myapplication.ui

import com.song2.myapplication.source.MovieData

interface MainContract {
    interface View {
        fun showGetMovieSuccess(movieDataList : List<MovieData>)
        fun showGetMovieFailure(e : Throwable)
        fun showMovieNotExist(cnt : Int)

        fun showGetMoreData(movieDataList : List<MovieData>)
    }

    interface Presenter {
        fun getMovie(keyword : String)
    }
}