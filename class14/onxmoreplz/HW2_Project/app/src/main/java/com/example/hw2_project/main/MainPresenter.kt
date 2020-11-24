package com.example.hw2_project.main

import com.example.hw2_project.data.repository.MovieRepositoryImpl

class MainPresenter (
    private val view : MainContract.View,
    private val repositoryImpl: MovieRepositoryImpl
) : MainContract.Presenter {

    override fun requestMovieListToRepo(query: String) {
        if(query.isEmpty()) {
            view.showErrorEmptyQuery()
        }else {
            repositoryImpl.getMovieList(
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