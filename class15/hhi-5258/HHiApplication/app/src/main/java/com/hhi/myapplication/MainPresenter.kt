package com.hhi.myapplication

import android.util.Log
import com.hhi.myapplication.data.repository.NaverRepositoryDataSourceImpl

class MainPresenter(
    private val view: MainContract.View,
    private val repositoryDataSourceImpl: NaverRepositoryDataSourceImpl
) : MainContract.Presenter {
    override fun searchMovie(query: String) {
        if (query.isEmpty()) {
            view.showEmptyQuery()
        } else {
            repositoryDataSourceImpl.searchMovies(query,
                success = { view.showMovies(it.items) },
                failed = { Log.e("search_failed", it.toString()); }
            )
        }
    }
}