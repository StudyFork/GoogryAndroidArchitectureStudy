package com.deepco.studyfork.presenter

import com.deepco.studyfork.data.repository.RepositoryMovieDataImpl

class MainPresenter(
    private val view: MainContract.View,
    private val repositoryMovieDataImpl: RepositoryMovieDataImpl
) : BasePresenter(), MainContract.Presenter {

    override fun queryMovie(query: String) {
        if (query.isEmpty()) {
            view.showToastMessage("영화 제목을 입력해주세요")
        } else {
            repositoryMovieDataImpl.getMovieList(query, {
                view.setMovieList(it)
            }, {
                view.showToastMessage(it)
            })
        }
    }

    override fun setRecentSearch(query: String) {
        if (query.isNotEmpty()) {
            repositoryMovieDataImpl.saveQuery(query)
        }
    }


}