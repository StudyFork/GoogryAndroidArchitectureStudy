package com.example.hw2_project.main

import com.example.hw2_project.data.repository.MovieRepository

class MainPresenter(
    private val view: MainContract.View,
    private val repository: MovieRepository
) : MainContract.Presenter {

    override fun requestMovieListToRepo(query: String) {
        if (query.isEmpty()) {
            view.showErrorEmptyQuery()
        } else {
            repository.getMovieList(
                query,
                success = {
                    view.showMovieList(it)
                },
                fail = {
                    view.showErrorRespondMsg(it)
                }
            )
        }
    }

}