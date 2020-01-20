package com.studyfork.architecturestudy.ui.main

import com.studyfork.architecturestudy.data.repository.MovieRepositoryImpl
import com.studyfork.architecturestudy.data.source.remote.MovieRemoteDataSourceImpl
import io.reactivex.disposables.CompositeDisposable

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    private val movieRepositoryImpl: MovieRepositoryImpl by lazy {
        MovieRepositoryImpl(MovieRemoteDataSourceImpl())
    }

    override fun getMovieList(query: String) {
        if (query.isEmpty()) {
            view.showErrorEmptyQuery()
            return
        }
        compositeDisposable.add(
            movieRepositoryImpl.getMovieList(query, {
                if (it) {
                    view.showLoading()
                } else {
                    view.hideLoading()
                }
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

    override fun onViewDetached() {
        compositeDisposable.clear()
    }

}