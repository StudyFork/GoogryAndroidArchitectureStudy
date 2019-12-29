package com.example.studyapplication.ui.main.movie

import com.example.studyapplication.data.model.SearchMovieResult

interface MovieContract {
    interface View {
        fun showList(items: Array<SearchMovieResult.MovieInfo>)
        fun toastErrorConnFailed(message : String)
    }

    interface Presenter {
        fun clickSearchButton(query : String)
    }
}