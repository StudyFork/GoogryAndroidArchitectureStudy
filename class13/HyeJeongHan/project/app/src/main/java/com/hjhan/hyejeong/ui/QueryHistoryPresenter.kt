package com.hjhan.hyejeong.ui

import com.hjhan.hyejeong.data.repository.NaverRepository

class QueryHistoryPresenter(
    private val view: QueryHistoryContract.View,
    private val repositoryImpl: NaverRepository
) : QueryHistoryContract.Presenter {

    override fun getRecentQueryList() {
        val list = repositoryImpl.getQueryList()

        if (list.isEmpty()) {
            view.emptyQueryList()
        } else {
            view.setRecentQueryList(list)
        }

    }
}