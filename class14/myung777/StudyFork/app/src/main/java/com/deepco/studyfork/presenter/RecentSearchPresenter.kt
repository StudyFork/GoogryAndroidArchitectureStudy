package com.deepco.studyfork.presenter

import com.deepco.studyfork.data.repository.RepositoryMovieDataImpl

class RecentSearchPresenter(
    private val view: RecentSearchContract.View,
    private val repositoryMovieDataImpl: RepositoryMovieDataImpl
) : BasePresenter(), RecentSearchContract.Presenter {
    override fun setRecentSearchList() {
        view.setRecentSearchList(repositoryMovieDataImpl.getQueryList())
    }

    override fun getRecentSearchMovie(query: String) {
        view.onRecentSearchMovie(query)
    }
}