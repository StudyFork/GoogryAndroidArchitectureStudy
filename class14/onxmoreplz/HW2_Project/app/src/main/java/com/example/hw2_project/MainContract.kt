package com.example.hw2_project

import com.example.hw2_project.data.api.NaverMovieData

interface MainContract {

    interface View {
        fun showErrorEmptyQuery()
        fun showMovieList(movieList: NaverMovieData.NaverMovieResponse)
        fun showErrorRespondMsg(t: Throwable)
    }

    interface Presenter {
        fun requestMovieListToRepo(query: String)
    }

}