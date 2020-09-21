package com.hong.architecturestudy.ui.main

import com.hong.architecturestudy.data.repository.RepositoryDataSource

class MainPresenter(
    private val view: MainContract.View,
    private val repositoryDataSource: RepositoryDataSource
) : MainContract.Presenter {

    override fun getMovieList(query: String) {
        if (query.isBlank()) {
            view.showQueryEmpty()
        } else {
            repositoryDataSource.getMovieList(query,
                {
                    view.showMovieList(it)
                }, {
                    view.showError(it)
                })
        }
    }
}