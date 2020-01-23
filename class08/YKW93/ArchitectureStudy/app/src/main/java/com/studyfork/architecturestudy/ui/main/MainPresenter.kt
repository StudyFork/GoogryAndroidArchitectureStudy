package com.studyfork.architecturestudy.ui.main

import com.studyfork.architecturestudy.base.BasePresenter
import com.studyfork.architecturestudy.data.repository.MovieRepositoryImpl
import com.studyfork.architecturestudy.data.source.remote.MovieRemoteDataSourceImpl

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter, BasePresenter() {

    private val movieRepositoryImpl: MovieRepositoryImpl by lazy {
        MovieRepositoryImpl(MovieRemoteDataSourceImpl())
    }

    override fun getMovieList(query: String) {
        if (query.isEmpty()) {
            view.showErrorEmptyQuery()
            return
        }
        subscribe(
            movieRepositoryImpl.getMovieList(query, {
                view.setLoadingVisible(it)
            }, {
                if (it.total != 0) {
                    view.showMovieList(it)
                } else {
                    view.showErrorEmptyResult()
                }

            }, {
                it.printStackTrace()
            })
        )
    }
}
