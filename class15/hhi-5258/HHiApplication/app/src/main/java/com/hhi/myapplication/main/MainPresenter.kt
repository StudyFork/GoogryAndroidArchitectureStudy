package com.hhi.myapplication.main

import android.util.Log
import com.hhi.myapplication.base.BasePresenter
import com.hhi.myapplication.data.repository.NaverRepositoryDataSourceImpl

class MainPresenter(
    private val view: MainContract.View,
    private val repositoryDataSourceImpl: NaverRepositoryDataSourceImpl
) : MainContract.Presenter, BasePresenter(view) {
    override fun searchMovie(query: String) {
        view.hideKeyboard()
        if (query.isEmpty()) {
            view.showEmptyQuery()
        } else {
            saveQuery(query)

            view.showProgressBar()
            repositoryDataSourceImpl.searchMovies(query,
                success = {
                    view.showMovies(it.items)
                    view.hideProgressBar()
                },
                failed = {
                    Log.e("search_failed", it.toString())
                    view.hideProgressBar()
                }
            )
        }
    }

    override fun saveQuery(query: String) {
        repositoryDataSourceImpl.saveQuery(query)
    }
}