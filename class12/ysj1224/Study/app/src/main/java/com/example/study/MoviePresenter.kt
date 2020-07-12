package com.example.study

import com.example.study.data.repository.MovieListRepositoryImpl

class MoviePresenter(
    private val view: MovieContract.View,
    private val repositoryImpl: MovieListRepositoryImpl
) : MovieContract.Presenter {

    override fun requestMovieList(query: String) {
        if(query.isEmpty()){
            showQueryEmpty()
        }else{
            doSearch(query)
        }

    }

    private fun doSearch(query: String) {
        repositoryImpl.doSearch(
            query = query,
            response = { view.showMovieList(it) },
            fail = { view.showErrorResponse(it) })
    }

    private fun showQueryEmpty() {
        view.showQueryEmpty()
    }
}
