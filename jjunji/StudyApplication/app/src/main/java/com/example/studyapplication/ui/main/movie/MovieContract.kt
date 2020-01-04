package com.example.studyapplication.ui.main.movie

import com.example.studyapplication.data.model.MovieInfo

interface MovieContract {
    interface View {
        fun showList(items: ArrayList<MovieInfo>)
        fun toastErrorConnFailed(message: String)
    }

    interface Presenter {
        fun clickSearchButton(query: String)
    }
}