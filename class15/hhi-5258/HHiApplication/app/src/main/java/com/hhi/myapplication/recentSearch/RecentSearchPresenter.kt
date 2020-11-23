package com.hhi.myapplication.recentSearch

import com.hhi.myapplication.base.BasePresenter
import com.hhi.myapplication.data.repository.NaverRepositoryDataSourceImpl

class RecentSearchPresenter(
    private val view: RecentSearchContract.View,
    private val naverRepositoryDataSource: NaverRepositoryDataSourceImpl
) : RecentSearchContract.Presenter, BasePresenter(view) {
    override fun searchRecentQuery() {
        val list = naverRepositoryDataSource.getQueryList()
        view.showQueryList(list)
    }
}