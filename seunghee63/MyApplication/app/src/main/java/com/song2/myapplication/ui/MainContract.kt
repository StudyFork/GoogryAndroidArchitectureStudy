package com.song2.myapplication.ui

import com.song2.myapplication.source.MovieData

interface MainContract {
    interface View {
        fun showGetMovieSuccess(movieDataList : List<MovieData>)
        fun showGetMovieFailure(e : Throwable)

        fun setResultVisible()
        fun setResultGone()

        fun getListCnt() : Int
    }

    interface Presenter {
        fun getMovie(keyword : String)
    }
}
