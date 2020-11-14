package com.deepco.studyfork.presenter

import com.deepco.studyfork.data.repository.RepositoryMovieDataImpl

class MainPresenter(
    private val view: MainContract.View,
    private val repositoryMovieDataImpl: RepositoryMovieDataImpl
) : MainContract.Presenter {

    override fun queryMovie(query: String) {
        if (query.isEmpty()) {
            view.showQueryEmpty()
        } else {
            repositoryMovieDataImpl.getMovieList(query, {
                view.setMovieList(it)
            }, {
                view.showMovieEmpty(it)
            })
        }
    }
}