package com.example.aas.ui.main

import com.example.aas.base.BaseContract
import com.example.aas.data.model.Movie
import io.reactivex.Single

interface MainContract {
    interface View : BaseContract.View {
        fun onSearchRequest()
        fun showSavedQuery(savedQuery: Single<List<String>>)
        fun showMovieResult(movieResult: Single<List<Movie>>)
    }

    interface Presenter : BaseContract.Presenter {
        fun getMovies(query: String)
        fun getSavedQueries()
    }
}