package com.jay.architecturestudy.ui.movie

import com.jay.architecturestudy.data.repository.NaverSearchRepositoryImpl

class MoviePresenter (
    private val view: MovieContract.View,
    private val repository: NaverSearchRepositoryImpl
): MovieContract.Presenter {
    override fun search(keyword: String) {
        repository.getMovie(
            keyword = keyword,
            success = { responseMovie ->
                view.updateResult(responseMovie.movies)
            },
            fail = { e ->
                val message = e.message ?: return@getMovie
                view.showErrorMessage(message)
            }
        )
    }
}