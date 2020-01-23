package com.studyfork.architecturestudy.ui.main

import com.studyfork.architecturestudy.base.BaseContract
import com.studyfork.architecturestudy.data.model.MovieResponse

interface MainContract {

    interface View : BaseContract.View {
        fun showMovieList(items: MovieResponse)
        fun showErrorEmptyQuery()
        fun showErrorEmptyResult()
    }

    interface Presenter : BaseContract.Presenter {
        fun getMovieList(query: String)
    }
}