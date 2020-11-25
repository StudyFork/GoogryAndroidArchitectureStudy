package com.example.hw2_project.recentSearch

import com.example.hw2_project.data.repository.MovieRepositoryImpl

class RecentSearchPresenter (
    private val view: RecentSearchContract.View,
    private val repositoryImpl: MovieRepositoryImpl
) : RecentSearchContract.Presenter {

    override fun searchRecentQuery() {
        val list = repositoryImpl.getSavedQuery()
        view.showRecentSearchMovieList(list)
    }

}