package com.example.studyapplication.main.movie

import com.example.studyapplication.data.model.SearchMovieResult

interface Contract {
    interface View {
        fun showList(items: Array<SearchMovieResult.MovieInfo>)
    }

    interface UserActions {
        fun clickSearchButton(query : String)
    }
}