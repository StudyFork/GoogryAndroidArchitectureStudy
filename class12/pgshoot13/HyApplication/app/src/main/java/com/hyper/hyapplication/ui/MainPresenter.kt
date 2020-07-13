package com.hyper.hyapplication.ui

import com.hyper.hyapplication.repository.NaverRepositoryImpl
import com.hyper.hyapplication.source.remote.NaverRemoteDataSourceImpl

class MainPresenter(private val viewAdapter: MainContract.View) : MainContract.Presenter {

    private val moviList = NaverRepositoryImpl(NaverRemoteDataSourceImpl())

    override fun movieSearch(query: String) {
        if (query.isNotEmpty()) {
            moviList.movieSearch(
                query,
                success = { viewAdapter.showMovie(it) },
                failure = {
                    viewAdapter.showFailure(it)
                })
        } else {
            viewAdapter.showEmptyMessage()
        }
    }
}