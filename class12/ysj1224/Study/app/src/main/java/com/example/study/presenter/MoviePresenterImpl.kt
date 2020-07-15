package com.example.study.presenter

import com.example.study.data.repository.MovieListRepositoryImpl

class MoviePresenterImpl(
    private val view: MovieContract.View,
    private val repositoryImpl: MovieListRepositoryImpl
) : MoviePresenter {

    override fun requestMovieList(query: String) {
        if (query.isEmpty()) {
            showQueryEmpty()
        } else {
            doSearch(query)
        }

    }

    override fun doSearch(query: String) {
        repositoryImpl.doSearch(
            query = query,
            response = { view.showMovieList(it) },
            fail = { view.showErrorResponse(it) })
    }

    override fun showQueryEmpty() {
        view.showQueryEmpty()
    }
}
