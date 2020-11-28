package com.architecture.androidarchitecturestudy.ui.searchhistory

import com.architecture.androidarchitecturestudy.data.repository.MovieRepositoryImpl
import com.architecture.androidarchitecturestudy.ui.base.BasePresenter

class SearchHistoryPresenter(
    private val view: SearchHistoryContract.View,
    private val movieRepositoryImpl: MovieRepositoryImpl
) : BasePresenter(), SearchHistoryContract.Presenter {

    override fun setSearchHistoryList() {
        view.setSearchHistoryList(movieRepositoryImpl.getSearchHistoryList())
    }

    override fun getSearchHistoryMovie(keyword: String) {
        view.onSearchHistoryMovie(keyword)
    }
}