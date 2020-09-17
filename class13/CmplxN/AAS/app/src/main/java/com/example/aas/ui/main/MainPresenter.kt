package com.example.aas.ui.main

import com.example.aas.base.BasePresenter
import com.example.aas.data.repository.MovieSearchRepository

class MainPresenter(
    override val view: MainContract.View,
    private val movieSearchRepository: MovieSearchRepository
) : BasePresenter(view), MainContract.Presenter {

    override fun getMovies(query: String) {
        view.onSearchRequest()
        view.showMovieResult(movieSearchRepository.getMovies(query).map { it.movies })
    }

    override fun getSavedQueries() = view.showSavedQuery(movieSearchRepository.getSavedQueries())

    override fun onDestroy() {
        movieSearchRepository.onDestroy()
        super.onDestroy()
    }
}