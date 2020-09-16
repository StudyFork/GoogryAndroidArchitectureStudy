package com.hong.architecturestudy.ui.moviedialog

import com.hong.architecturestudy.data.repository.RepositoryDataSource

class MovieListDialogPresenter(
    private val view: MovieListDialogContract.View,
    private val repositoryDataSource: RepositoryDataSource
) : MovieListDialogContract.Presenter {

    override fun loadRecentSearchMovieList() {
        view.loadRecentQuery(repositoryDataSource.loadRecentSearchQuery())
    }
}