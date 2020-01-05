package com.hansung.firstproject.ui.main

import com.hansung.firstproject.data.repository.NaverRepository

class MainPresenter(
    private val view: MainContract.View,
    private val repository: NaverRepository
) : MainContract.Presenter {
    override fun init() {
        view.initRecyclerView()
    }

    override fun doSearch(keyword: String) {
        if (keyword.isEmpty()) {
            view.showErrorKeywordEmpty()
        } else {
            // 검색 메소드
            repository.getMoviesData(keyword,
                success = {
                    view.addItemToAdapter(it)
                },
                fail = {
                    view.showErrorInternetDisconnect()
                })
        }
    }
}