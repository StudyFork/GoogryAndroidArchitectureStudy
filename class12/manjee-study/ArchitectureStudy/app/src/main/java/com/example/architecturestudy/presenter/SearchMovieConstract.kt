package com.example.architecturestudy.presenter

import com.example.architecturestudy.data.model.MovieData

interface SearchMovieConstract {

    interface View {
        fun showMovieList(movieList: List<MovieData>)
        fun showSearchFailToast(throwable: Throwable)
    }

    interface Presenter {
        fun searchMovieOnRemote(movieTitle: String)
    }
}