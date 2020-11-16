package com.hhi.myapplication

import android.util.Log
import com.hhi.myapplication.data.repository.NaverRepositoryDataSourceImpl

class MainPresenter(
    private val view: MainContract.View,
    private val repositoryDataSourceImpl: NaverRepositoryDataSourceImpl
) : MainContract.Presenter {
    override fun searchMovie(query: String) {
        view.hideKeyboard()
        if (query.isEmpty()) {
            view.showEmptyQuery()
        } else {
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
}