package com.example.architecturestudy.ui.movie

interface MovieContract {

    interface MovieView {
        fun showError(message : String)
    }

    interface MoviePresent {
        fun activeError(error : Throwable)
    }
}