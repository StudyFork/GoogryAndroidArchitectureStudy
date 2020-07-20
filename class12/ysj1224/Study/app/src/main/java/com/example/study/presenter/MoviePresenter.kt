package com.example.study.presenter

import com.example.study.data.repository.MovieListRepository

class MoviePresenter(
    private val view: MovieContract.View,
    private val repository: MovieListRepository
) : MovieContract.Presenter {

    override fun requestMovieList(query: String) {
        if (query.isEmpty()) {
            showQueryEmpty()
        } else {
            doSearch(query)
        }
    }

    fun doSearch(query: String) {
        repository.doSearch(
            query = query,
            response = { view.showMovieList(it) },
            fail = { view.showErrorResponse(it) })
    }

    fun showQueryEmpty() {
        view.showQueryEmpty()
    }
}
