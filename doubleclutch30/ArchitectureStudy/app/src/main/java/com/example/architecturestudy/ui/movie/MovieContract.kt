package com.example.architecturestudy.ui.movie

interface MovieContract {

    interface View {
        fun showErrorMessage(message : String)
    }

    interface Present {
        fun searchMovie(keyword : String)
        fun taskError(error : Throwable)
    }
}