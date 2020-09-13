package com.hong.architecturestudy.ui.moviedialog

import com.hong.architecturestudy.data.repository.RepositoryDataSource
import com.hong.architecturestudy.ui.moviedialog.adapter.MovieSearchListAdapter

class MovieListDialogPresenter(
    private val view: MovieListDialogContract.View,
    private val repositoryDataSource: RepositoryDataSource,
    private val movieSearchListAdapter: MovieSearchListAdapter
) : MovieListDialogContract.Presenter {

    override fun loadResentSearchMovieList() {
        movieSearchListAdapter.setList(repositoryDataSource.loadResentSearchQuery())
    }
}