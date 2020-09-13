package com.hong.architecturestudy.ui.main

import com.hong.architecturestudy.data.repository.RepositoryDataSource
import com.hong.architecturestudy.ui.main.adapter.MovieAdapter

class MainPresenter(
    private val view: MainContract.View,
    private val repositoryDataSource: RepositoryDataSource,
    private val movieAdapter: MovieAdapter
) : MainContract.Presenter {

    override fun getMovieList(query: String) {
        if (query.isBlank()) {
            view.showQueryEmpty()
        } else {
            repositoryDataSource.getMovieList(query,
                {
                    movieAdapter.setData(it)
                }, {
                    view.showError(it)
                })
        }
    }
}