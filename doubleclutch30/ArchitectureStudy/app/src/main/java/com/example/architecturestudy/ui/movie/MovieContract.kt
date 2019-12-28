package com.example.architecturestudy.ui.movie

import com.example.architecturestudy.data.model.MovieItem

interface MovieContract {

    interface View {
        fun showErrorMessage(message : String)
        fun showResult(item: List<MovieItem>)
    }

    interface Presenter {
        fun searchMovie(keyword : String)
        fun taskError(error : Throwable)
    }
}